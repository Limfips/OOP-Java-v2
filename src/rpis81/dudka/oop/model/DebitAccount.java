package rpis81.dudka.oop.model;

import java.time.LocalDate;

public class DebitAccount extends AbstractAccount implements Cloneable {

    public DebitAccount(String number, LocalDate expirationDate) {
        super(number, expirationDate);
    }

    public DebitAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) {
        super(number, balance, creationDate, expirationDate);
        if (!isValidBalance(balance)) throw new IllegalArgumentException();
    }

    private boolean isValidBalance(double balance) {
        return balance >= 0;
    }

    @Override
    public String toString() {
        return String.format("DebitAccount - %s", super.toString());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode() * 53;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
