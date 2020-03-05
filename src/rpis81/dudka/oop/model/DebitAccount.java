package rpis81.dudka.oop.model;

public class DebitAccount extends AbstractAccount implements Cloneable {

    public DebitAccount() {
        super();
    }

    public DebitAccount(String number, double balance) {
        super(number, balance);
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
