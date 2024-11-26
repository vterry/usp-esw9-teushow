package entity;

import exception.PedidoDomainException;
import valueobjects.*;

import java.util.List;
import java.util.UUID;

public class PedidoDeIngresso extends AggregateRoot<IdPedido> {
    private final IdCliente idCliente;
    private final TipoEntrega tipoEntrega;
    private final Endereco localRetirada;
    private final List<Ingresso> ingressos;
    private final Monetario preco;
    private final boolean transferivel;

    private StatusPedido statusPedido;
    private List<String> historicoFalhas;

    public static final String DELIMITADOR_MENSAGENS_FALHA = ",";

    public void inicializarPedido() {
        setId(new IdPedido(UUID.randomUUID()));
        inicializarIngresssos();
    }

    public void pagar(){
        if(statusPedido != StatusPedido.RESERVADO){
            throw new PedidoDomainException("Pedido não está no status correto para realizar pagamento.");
        }
        statusPedido = StatusPedido.PAGO;
    }

    public void concluir(){
        if(statusPedido != StatusPedido.PAGO){
            throw new PedidoDomainException("Pedido não está no status correto para ser concluído.");
        }
        statusPedido = StatusPedido.CONCLUIDO;
    }

    public void iniciarCancelamento(List<String> historicoFalhas){
        if(statusPedido != StatusPedido.PAGO){
            throw new PedidoDomainException("Este pedido não permiti que o cancelamento seja iniciado.");
        }
        statusPedido = StatusPedido.CANCELANDO;
        atualizaraHistoricoFalha(historicoFalhas);
    }

    public void cancelar(List<String> historicoFalhas) {
        if(!(statusPedido != StatusPedido.CANCELANDO || statusPedido != StatusPedido.RESERVADO)){
            throw new PedidoDomainException("Este pedido não pode ser cancelado.");
        }
        statusPedido = StatusPedido.CANCELADO;
        atualizaraHistoricoFalha(historicoFalhas);
    }

    private void atualizaraHistoricoFalha(List<String> historicoFalhas) {
        if (this.historicoFalhas != null && historicoFalhas != null) {
            this.historicoFalhas.addAll(historicoFalhas.stream().filter(message -> !message.isEmpty()).toList());
        }
        if (this.historicoFalhas == null) {
            this.historicoFalhas = historicoFalhas;
        }
    }

    private void validarInicializacaoPedido(){
        if(statusPedido == null || getId() != null){
            throw new PedidoDomainException("Pedido não pode ser inicializado");
        }
    }

    private void validarPrecoTotal(){
        if (preco == null || !preco.eMaiorQueZero()){
            throw new PedidoDomainException("Total precisa ser maior que zero.");
        }
    }

    private void validarPrecoIngressos(){
        Monetario totalIngressos = ingressos.stream().map(ingresso -> {
            validarPrecoIngresso(ingresso);
            return ingresso.getValorTotal();
        }).reduce(Monetario.ZERO, Monetario::adicionar);

        if (!preco.equals(totalIngressos)) {
            throw new PedidoDomainException("Total price: " + preco.getQuantidade()
                    + " is not equal to Order items total: " + totalIngressos.getQuantidade() + "!");
        }
    }

    private void inicializarIngresssos() {
        long itemId = 1;
        for (Ingresso ingresso: ingressos) {
            ingresso.inicializarItemPedido(super.getId(), new IdItemPedido(itemId++));
        }
    }

    // TODO - Trazer o preço do dominio de Eventos
    private void validarPrecoIngresso(Ingresso ingresso){
        if (!ingresso.precoEhValido()) {
            throw new PedidoDomainException("Order item price: " + ingresso.getValorTotal().getQuantidade() +
                    " is not valid for product " + ingresso.getIdEvento());
        }
    }

    private PedidoDeIngresso(Builder builder){
        this.idCliente = builder.idCliente;
        this.tipoEntrega = builder.tipoEntrega;
        this.localRetirada = builder.localRetirada;
        this.ingressos = builder.ingressos;
        this.preco = builder.preco;
        this.transferivel = builder.transferivel;
        this.statusPedido = builder.statusPedido;
    }

    private static final class Builder {
        private IdPedido idPedido;
        private IdCliente idCliente;
        private TipoEntrega tipoEntrega;
        private Endereco localRetirada;
        private List<Ingresso> ingressos;
        private Monetario preco;
        private boolean transferivel;
        private StatusPedido statusPedido;

        private Builder() {
        }

        public Builder idPedido(IdPedido val) {
            idPedido = val;
            return this;
        }
        public Builder idCliente(IdCliente val) {
            idCliente = val;
            return this;
        }
        public Builder tipoEntrega(TipoEntrega val) {
            tipoEntrega = val;
            return this;
        }
        public Builder localRetirada(Endereco val) {
            localRetirada = val;
            return this;
        }
        public Builder ingressos(List<Ingresso> val) {
            ingressos = val;
            return this;
        }
        public Builder preco(Monetario val) {
            preco = val;
            return this;
        }
        public Builder transferivel(boolean val) {
            transferivel = val;
            return this;
        }

        public Builder statusPedido(StatusPedido val) {
            statusPedido = val;
            return this;
        }

        public PedidoDeIngresso build() {
            return new PedidoDeIngresso(this);
        }
    }


}
