package rpis81.dudka.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractAccountTest {

    private final String number = "SecondAbstractAccount";
    private final double balance = 34142;

    private AbstractAccount getFirstConstructor() {
        return new AbstractAccount();
    }

    private AbstractAccount getSecondConstructor() {
        return new AbstractAccount(number, balance);
    }

    @Test
    public void getNumber() {
        AbstractAccount abstractAccount;

        abstractAccount = getFirstConstructor();
        assertEquals(abstractAccount.getNumber(), AbstractAccount.NUMBER_DEFAULT);

        abstractAccount = getSecondConstructor();
        assertEquals(abstractAccount.getNumber(), number);
    }

    @Test
    public void setNumber() {
        AbstractAccount abstractAccount;
        String newNumber = "asfasfasfa";

        abstractAccount = getFirstConstructor();
        abstractAccount.setNumber(newNumber);
        assertEquals(abstractAccount.getNumber(), newNumber);

        abstractAccount = getSecondConstructor();
        abstractAccount.setNumber(newNumber);
        assertEquals(abstractAccount.getNumber(), newNumber);
    }

    @Test
    public void getBalance() {
        AbstractAccount abstractAccount;

        abstractAccount = getFirstConstructor();
        assertEquals(abstractAccount.getBalance(), AbstractAccount.BALANCE_DEFAULT, 0.0);

        abstractAccount = getSecondConstructor();
        assertEquals(abstractAccount.getBalance(), balance, 0.0);
    }

    @Test
    public void setBalance() {
        AbstractAccount abstractAccount;
        double newBalance = 23534634;

        abstractAccount = getFirstConstructor();
        abstractAccount.setBalance(newBalance);
        assertEquals(abstractAccount.getBalance(), newBalance, 0.0);

        abstractAccount = getSecondConstructor();
        abstractAccount.setBalance(newBalance);
        assertEquals(abstractAccount.getBalance(), newBalance, 0.0);
    }
}