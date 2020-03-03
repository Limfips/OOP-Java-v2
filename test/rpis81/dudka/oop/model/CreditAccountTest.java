package rpis81.dudka.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreditAccountTest {

    @Test
    public void firstInit() {
        CreditAccount creditAccount = new CreditAccount();
        assertEquals(creditAccount.getAPR(), CreditAccount.DEFAULT_APR, 0.0);
        assertEquals(creditAccount.getBalance(), CreditAccount.BALANCE_DEFAULT, 0.0);
        assertEquals(creditAccount.getNumber(), CreditAccount.NUMBER_DEFAULT);
        double testAPR = 10;
        creditAccount.setAPR(testAPR);
        assertEquals(creditAccount.getAPR(), testAPR, 0.0);
    }

    @Test
    public void secondTest() {
        String testNumber ="TestNumber";
        double testBalance = 2235345;
        double firstTestAPR = 10;

        CreditAccount creditAccount = new CreditAccount(testNumber, testBalance, firstTestAPR);
        assertEquals(creditAccount.getAPR(), firstTestAPR, 0.0);
        assertEquals(creditAccount.getBalance(), testBalance, 0.0);
        assertEquals(creditAccount.getNumber(), testNumber);
        double secondTestAPR = 10;
        creditAccount.setAPR(secondTestAPR);
        assertEquals(creditAccount.getAPR(), secondTestAPR, 0.0);
    }
}