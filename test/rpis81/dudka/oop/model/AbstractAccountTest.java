package rpis81.dudka.oop.model;

import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class AbstractAccountTest {

    private AbstractAccount account;
    private DataSource source = new DataSource();

    @Test
    public void firstInit() {
        account = new AbstractAccount(source.testNumbers[0], source.expirationDate);
        assertNotNull(account);
    }

    @Test
    public void secondInit() {
        account = new AbstractAccount(source.testNumbers[1], source.testDebitBalance[0],
                source.testCreationDate, source.testExpirationDate);
        assertNotNull(account);
    }

    private AbstractAccount getAccount() {
        return  new AbstractAccount(source.testNumbers[1], source.testDebitBalance[0],
                source.testCreationDate, source.testExpirationDate);
    }

    @Test
    public void setNumber() {
        account = getAccount();
        try {
            account.setNumber(source.testNumbers[source.testNumbers.length-4]);
        } catch (InvalidAccountNumberException ex) {

        }
        try {
            account.setNumber(source.testNumbers[source.testNumbers.length-3]);
        } catch (InvalidAccountNumberException ex) {

        }
        try {
            account.setNumber(source.testNumbers[source.testNumbers.length-2]);
        } catch (InvalidAccountNumberException ex) {

        }
        try {
            account.setNumber(source.testNumbers[source.testNumbers.length-1]);
        } catch (NullPointerException ex) {

        }
    }

    @Test
    public void setExpirationDate() {
        account = getAccount();
        account.setExpirationDate(source.newTestExpirationDate);
    }

    @Test
    public void monthsQuantityBeforeExpiration() {
        //Этот тест зависит от LocalDate.now(), так что перед тестами сверся с датой
        account = getAccount();
        assertEquals(4, account.monthsQuantityBeforeExpiration());
    }

    @Test
    public void compareTo() {
        AbstractAccount firstAbstractAccount = new AbstractAccount(source.testNumbers[0], source.expirationDate);
        AbstractAccount secondAbstractAccount = new AbstractAccount(source.testNumbers[0], source.expirationDate);

        firstAbstractAccount.setBalance(10);
        secondAbstractAccount.setBalance(50);
        assertTrue(firstAbstractAccount.compareTo(secondAbstractAccount) < 0);
    }
}