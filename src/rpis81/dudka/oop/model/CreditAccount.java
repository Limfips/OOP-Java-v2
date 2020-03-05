package rpis81.dudka.oop.model;

import java.util.Objects;

public class CreditAccount extends AbstractAccount implements Credit, Cloneable {

    public static final double DEFAULT_APR = 30;
    private double APR;

    public CreditAccount() {
        super();
        this.APR = DEFAULT_APR;
    }

    public CreditAccount(String number, double balance, double APR) {
        super(number, balance);
        this.APR = APR;
    }

    @Override
    public double getAPR() {
        return APR;
    }

    @Override
    public void setAPR(double APR) {
        this.APR = APR;
    }

    @Override
    public String toString() {
        return String.format("CreditAccount - %s", super.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditAccount)) return false;
        if (!super.equals(o)) return false;
        CreditAccount that = (CreditAccount) o;
        return Double.compare(that.APR, APR) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), APR) * 71;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
