package rpis81.dudka.oop.model;

public class AbstractAccount implements Account {

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

    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractAccount{");
        sb.append("number='").append(number).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
