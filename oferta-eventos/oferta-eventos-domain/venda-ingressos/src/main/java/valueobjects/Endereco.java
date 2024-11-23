package valueobjects;

import java.util.Objects;
import java.util.UUID;

public class Endereco {

    private final UUID id;
    private final String rua;
    private final String codigoPostal;
    private final String cidade;

    public Endereco(UUID id, String rua, String codigoPostal, String cidade) {
        this.id = id;
        this.rua = rua;
        this.codigoPostal = codigoPostal;
        this.cidade = cidade;
    }

    public UUID getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(rua, endereco.rua) && Objects.equals(codigoPostal, endereco.codigoPostal) && Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rua, codigoPostal, cidade);
    }
}
