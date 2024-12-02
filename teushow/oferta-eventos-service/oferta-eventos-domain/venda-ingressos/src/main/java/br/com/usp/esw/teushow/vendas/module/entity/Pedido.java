package br.com.usp.esw.teushow.vendas.module.entity;

import br.com.usp.esw.common.domain.entity.AggregateRoot;
import br.com.usp.esw.common.domain.valueobjects.*;
import br.com.usp.esw.teushow.vendas.module.exception.VendaIngressoModuleException;
import br.com.usp.esw.teushow.vendas.module.valueobject.Endereco;
import br.com.usp.esw.teushow.vendas.module.valueobject.IdItemPedido;
import br.com.usp.esw.teushow.vendas.module.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

public class Pedido extends AggregateRoot<IdPedido> {
    private final IdCliente idCliente;
    private final IdEvento idEvento;
    private final TipoEntrega tipoEntrega;
    private final Endereco localRetirada;
    private final Money preco;
    private final List<ItemPedido> ingressos;

    private TrackingId trackingId;
    private StatusDoPedido statusDoPedido;
    private List<String> historicoFalhas;

    public static final String FAILURE_MESSAGE_DELIMITER = ",";


    public void inicializarPedido(){
        setId(new IdPedido(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        statusDoPedido = StatusDoPedido.RESERVADO;
        inicializarItemPedido();
    }

    public void inicializarItemPedido(){
        long itemId = 1;
        for (ItemPedido ingresso : ingressos){
            ingresso.inicializarItemPedido(super.getId(), new IdItemPedido(itemId++));
        }
    }

    public void validarPedido(){
        validarPedidoInicial();
        validarPrecoTotal();
        validarPrecoItensPedido();
    }

    public void pagar(){
        if(statusDoPedido != StatusDoPedido.RESERVADO){
            throw new VendaIngressoModuleException("Pedido não está no estado correto para pagamento!");
        }
        statusDoPedido = StatusDoPedido.PAGO;
    }

    public void aprovar(){
        if(statusDoPedido != StatusDoPedido.PAGO){
            throw new VendaIngressoModuleException("Pedido não está no estado correto para aprovação!");
        }
        statusDoPedido = StatusDoPedido.APROVADO;
    }

    public void iniciarCancelamento(List<String> historicoFalhas){
        if (statusDoPedido != StatusDoPedido.PAGO){
            throw new VendaIngressoModuleException("Pedido não permite que o cancelamento seja iniciado.");
        }
        statusDoPedido = StatusDoPedido.CANCELANDO;
        atualizarHistoricoFalhas(historicoFalhas);
    }

    public void cancelar(List<String> historicoFalhas){
        if (!(statusDoPedido == StatusDoPedido.CANCELANDO || statusDoPedido == StatusDoPedido.RESERVADO)){
            throw new VendaIngressoModuleException("Pedido não pode ser cancelado.");
        }
        statusDoPedido = StatusDoPedido.CANCELADO;
        atualizarHistoricoFalhas(historicoFalhas);
    }

    private void validarPedidoInicial(){
        if (statusDoPedido != null || getId() == null){
            throw new VendaIngressoModuleException("Pedido com estado inválido para inicialização!");
        }
    }

    private void validarPrecoTotal(){
        if (preco == null || !preco.isGreaterThanZero()){
            throw new VendaIngressoModuleException("Preco total precisa ser maior que zero!");
        }
    }

    private void validarPrecoItensPedido(){
        Money precoTotalIngressos = ingressos.stream().map(itemPedido -> {
            validarPrecoItem(itemPedido);
            return itemPedido.getSubTotal();
        }).reduce(Money.ZERO, Money::add);

        if (!preco.equals(precoTotalIngressos)){
            throw new VendaIngressoModuleException("Preco total: " + preco.getAmount() +
                    " não é igual ao preco total dos itens do pedido:" + precoTotalIngressos.getAmount() + "!");
        }
    }

    private void validarPrecoItem(ItemPedido itemPedido){
        if (!itemPedido.isPrecoValido()){
            throw new VendaIngressoModuleException("Valor do preço do item: " + itemPedido.getPreco().getAmount() +
                    " não é válido para o ingresso " + itemPedido.getIngresso().getId().getValue());
        }
    }

    private void atualizarHistoricoFalhas(List<String> historicoFalhas){
        if(this.historicoFalhas != null && historicoFalhas != null){
            this.historicoFalhas.addAll(historicoFalhas.stream().filter(mensagem -> !mensagem.isEmpty()).toList());
        }
        if (this.historicoFalhas == null){
            this.historicoFalhas = historicoFalhas;
        }
    }

    private Pedido(Builder builder) {
        super.setId(builder.idPedido);
        idCliente = builder.idCliente;
        idEvento = builder.idEvento;
        tipoEntrega = builder.tipoEntrega;
        localRetirada = builder.localRetirada;
        preco = builder.preco;
        ingressos = builder.ingressos;
        trackingId = builder.trackingId;
        statusDoPedido = builder.statusDoPedido;
        historicoFalhas = builder.historicoFalhas;
    }

    public static Builder builder() {
        return new Builder();
    }

    public IdCliente getIdCliente() {
        return idCliente;
    }

    public IdEvento getIdEvento() {
        return idEvento;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public Endereco getLocalRetirada() {
        return localRetirada;
    }

    public Money getPreco() {
        return preco;
    }

    public List<ItemPedido> getIngressos() {
        return ingressos;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public StatusDoPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public List<String> getHistoricoFalhas() {
        return historicoFalhas;
    }

    public static final class Builder {
        private IdPedido idPedido;
        private IdCliente idCliente;
        private IdEvento idEvento;
        private TipoEntrega tipoEntrega;
        private Endereco localRetirada;
        private Money preco;
        private List<ItemPedido> ingressos;
        private TrackingId trackingId;
        private StatusDoPedido statusDoPedido;
        private List<String> historicoFalhas;

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

        public Builder idEvento(IdEvento val) {
            idEvento = val;
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

        public Builder preco(Money val) {
            preco = val;
            return this;
        }

        public Builder ingressos(List<ItemPedido> val) {
            ingressos = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder statusDoPedido(StatusDoPedido val) {
            statusDoPedido = val;
            return this;
        }

        public Builder historicoFalhas(List<String> val) {
            historicoFalhas = val;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }
}
