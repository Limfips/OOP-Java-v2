package rpis81.dudka.oop.model;

public class CreditAccount extends AbstractAccount implements Credit {

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
}
