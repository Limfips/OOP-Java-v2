package rpis81.dudka.oop.model;

import java.util.Objects;

public class AbstractAccount implements Account, Cloneable {

    public static final String NUMBER_DEFAULT = "";
    public static final double BALANCE_DEFAULT = 0;
    private String number;
    private double balance;

    protected AbstractAccount() {
        this(NUMBER_DEFAULT, BALANCE_DEFAULT);
    }

    protected AbstractAccount(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("number: %s balance: %f", number, balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
