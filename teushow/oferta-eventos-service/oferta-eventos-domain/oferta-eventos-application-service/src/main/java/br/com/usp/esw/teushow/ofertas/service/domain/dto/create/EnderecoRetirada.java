package br.com.usp.esw.teushow.ofertas.service.domain.dto.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

public class EnderecoRetirada {

    @NotNull
    @Max(value = 50)
    private final String rua;
    @NotNull
    @Max(value = 10)
    private final String codigoPostal;
    @NotNull
    @Max(value = 10)
    private final String cidade;

    private EnderecoRetirada(Builder builder) {
        rua = builder.rua;
        codigoPostal = builder.codigoPostal;
        cidade = builder.cidade;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getRua() {
        return rua;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getCidade() {
        return cidade;
    }

    public static final class Builder {
        private String rua;
        private String codigoPostal;
        private String cidade;

        private Builder() {
        }

        public Builder rua(String val) {
            rua = val;
            return this;
        }

        public Builder codigoPostal(String val) {
            codigoPostal = val;
            return this;
        }

        public Builder cidade(String val) {
            cidade = val;
            return this;
        }

        public EnderecoRetirada build() {
            return new EnderecoRetirada(this);
        }
    }
}
