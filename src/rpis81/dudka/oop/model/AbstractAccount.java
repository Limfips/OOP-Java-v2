package rpis81.dudka.oop.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.MONTHS;

public class AbstractAccount implements Account, Cloneable {

    public static final double BALANCE_DEFAULT = 0;
    public static final LocalDate CREATION_DATE_DEFAULT = LocalDate.now();
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    public static Pattern pattern = Pattern.compile("[4][450][0-9][0-9][0-9]" + "810" + "[0-9]"
            + "[0-9][0-9][0-9][0-9]" + "[0-9][0-9][0-9][0-9][0-9][0-9][0-9]");

    private String number;
    private double balance;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    protected AbstractAccount(String number, LocalDate expirationDate) {
        this(number, BALANCE_DEFAULT, CREATION_DATE_DEFAULT, expirationDate);
    }

    protected AbstractAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate) {
        setCreationDate(creationDate);
        setExpirationDate(expirationDate);
        setNumber(number);
        setBalance(balance);
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void setNumber(String number) {
        if (number == null) throw new NullPointerException();
        if (!isValidNumber(number)) throw new InvalidAccountNumberException("Error: InvalidAccountNumber");
        this.number = number;
    }

    private boolean isValidNumber(String number) {
        return pattern.matcher(number).matches();
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
    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        if (creationDate == null) throw new NullPointerException();
        if (creationDate.isAfter(CREATION_DATE_DEFAULT)) throw new IllegalArgumentException();
        this.creationDate = creationDate;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(LocalDate date) {
        if (date == null) throw new NullPointerException();
        if (date.isBefore(creationDate)) throw new IllegalArgumentException();
        this.expirationDate = date;
    }

    @Override
    public int monthsQuantityBeforeExpiration() {
        LocalDate nowDate = LocalDate.now();
        int months = (int) MONTHS.between(nowDate, expirationDate);
        return (nowDate.getDayOfMonth() < 25) ? months + 1: months;
    }

    @Override
    public String toString() {
        return String.format("number: %s balance: %f creationDate: %s expirationDate: %s",
                number, balance, creationDate.format(formatter), expirationDate.format(formatter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;
        AbstractAccount that = (AbstractAccount) o;
        return Double.compare(that.balance, balance) == 0 &&
                Objects.equals(number, that.number) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance, creationDate, expirationDate);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Account o) {
        return ((int) (this.balance - o.getBalance()));
    }
}
