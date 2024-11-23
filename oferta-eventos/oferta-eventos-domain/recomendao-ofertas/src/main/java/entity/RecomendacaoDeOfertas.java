package entity;

import valueobjects.*;

import java.util.List;


public class RecomendacaoDeOfertas extends AggregateRoot<IdRecomendacao> {
    private final IdEvento idEvento;
    private final IdPerfil idPerfil;
    private final Categoria categoria;
    private final int scoreDeVenda;

    private IdFeedback idFeedback;
    private boolean deveMostrar;

    //TODO - implementar um gerador de ofertas aleatório
    public List<RecomendacaoDeOfertas> listarRecomendacoes(IdPerfil idPerfil){
        return null;
    }

    public void configurarPreferencia(boolean deveMostrar){
        this.deveMostrar = deveMostrar;
    }

    public void registrarFeedback(IdFeedback idFeedback){
        this.idFeedback = idFeedback;
    }

    private RecomendacaoDeOfertas (Builder builder){
        this.setId(builder.idRecomendacao);
        this.idEvento = builder.idEvento;
        this.idPerfil = builder.idPerfil;
        this.categoria = builder.categoria;
        this.idFeedback = builder.idFeedback;
        this.deveMostrar = builder.deveMostrar;
        this.scoreDeVenda = builder.scoreDeVenda;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder{
        private IdRecomendacao idRecomendacao;
        private IdEvento idEvento;
        private IdPerfil idPerfil;
        private Categoria categoria;
        private IdFeedback idFeedback;
        private boolean deveMostrar;
        private int scoreDeVenda;

        public Builder(){}

        public Builder idRecomendacao (IdRecomendacao val) {
            idRecomendacao = val;
            return this;
        }
        public Builder idEvento (IdEvento val) {
            idEvento = val;
            return this;
        }
        public Builder idPerfil (IdPerfil val) {
            idPerfil = val;
            return this;
        }
        public Builder categoria (Categoria val) {
            categoria = val;
            return this;
        }
        public Builder idFeedback (IdFeedback val) {
            idFeedback = val;
            return this;
        }
        public Builder deveMostrar (boolean val) {
            deveMostrar = val;
            return this;
        }
        public Builder scoreDeVenda (int val) {
            scoreDeVenda = val;
            return this;
        }

        public RecomendacaoDeOfertas build() {
            return new RecomendacaoDeOfertas(this);
        }
    }
}
