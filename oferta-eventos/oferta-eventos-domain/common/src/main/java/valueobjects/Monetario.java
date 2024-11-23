package valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Monetario {
    private final BigDecimal quantidade;

    public static final Monetario ZERO = new Monetario(BigDecimal.ZERO);

    public Monetario(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public boolean eMaiorQueZero() {
        return this.quantidade != null && this.quantidade.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean eMaiorQue(Monetario monetario) {
        return this.quantidade != null && this.quantidade.compareTo(monetario.getquantidade()) > 0;
    }

    public Monetario adicionar(Monetario monetario) {
        return new Monetario(setScale(this.quantidade.add(monetario.getquantidade())));
    }

    public Monetario subtrair(Monetario monetario) {
        return new Monetario(setScale(this.quantidade.subtract(monetario.getquantidade())));
    }

    public Monetario multiplicar(int multiplier) {
        return new Monetario(setScale(this.quantidade.multiply(new BigDecimal(multiplier))));
    }

    public BigDecimal getquantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monetario monetario = (Monetario) o;
        return quantidade.equals(monetario.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantidade);
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, RoundingMode.HALF_EVEN);
    }
}