package rpis81.dudka.oop.model;

public class DebitAccount extends AbstractAccount {

    public DebitAccount() {
        super();
    }

    public DebitAccount(String number, double balance) {
        super(number, balance);
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("DebitAccount{");
        sb.append(super.toString());
        sb.append('}');
        return sb.toString();
    }
}
