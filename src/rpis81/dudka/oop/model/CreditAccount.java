package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.YEARS;

public class CreditAccount extends AbstractAccount implements Credit, Cloneable {

    public static final double DEFAULT_APR = 30;
    private double APR;

    public CreditAccount(String number, LocalDate expirationDate) {
        super(number, expirationDate);
        this.APR = DEFAULT_APR;
    }

    public CreditAccount(String number, double balance, double APR, LocalDate creationDate, LocalDate expirationDate) {
        super(number, balance, creationDate, expirationDate);
        if (!isValidBalance(balance)) throw new IllegalArgumentException();
        this.APR = APR;
    }

    private boolean isValidBalance(double balance) {
        return balance <= 0;
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
    public double nextPaymentValue() {
        return getBalance() * (1 + APR * getRestYear()) / monthsQuantityBeforeExpiration();
    }

    private double getRestYear() {
        return YEARS.between(LocalDate.now(), getExpirationDate());
    }

    @Override
    public LocalDate nextPaymentDay() {
        LocalDate today = LocalDate.now();
        if (today.getDayOfMonth() < 25) {
            return LocalDate.of(today.getYear(), today.getMonth(), 25);
        } else {
            return LocalDate.of(today.getYear(), today.getMonthValue() + 1, 25);
        }
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
