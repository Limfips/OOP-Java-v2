package rpis81.dudka.oop.model;

public class Account {

    public static final String NUMBER_DEFAULT = "";
    public static final double BALANCE_DEFAULT = 0;
    private String number;
    private double balance;

    public Account() {
        this(NUMBER_DEFAULT, BALANCE_DEFAULT);
    }

    public Account(String number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("number='").append(number).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
