package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import static org.junit.Assert.*;

public class EntityTest {

    private Entity entity;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        entity = (Entity) source.clients[1];
    }

    @Test
    public void add() {
        try {
            assertTrue(entity.add(source.testAccounts[20]));
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add1() {
        try {
            assertTrue(entity.add(0, source.testAccounts[20]));
            assertEquals(entity.get(0), source.testAccounts[20]);
            assertEquals(entity.get(1), source.testAccounts[5]);
            assertEquals(entity.get(2), source.testAccounts[6]);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        assertEquals(entity.get(source.testAccounts[5].getNumber()), source.testAccounts[5]);
    }

    @Test
    public void get1() {
        assertEquals(entity.get(0), source.testAccounts[5]);
    }

    @Test
    public void hasAccount() {
        assertTrue(entity.hasAccount(source.testAccounts[5].getNumber()));
    }

    @Test
    public void set() {
        try {
            assertEquals(entity.set(0, source.testAccounts[20]), source.testAccounts[5]);
            assertEquals(entity.get(0), source.testAccounts[20]);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        assertEquals(entity.remove(0), source.testAccounts[5]);
        assertEquals(entity.get(0), source.testAccounts[6]);
        assertEquals(entity.get(1), source.testAccounts[7]);
        assertEquals(entity.get(2), source.testAccounts[8]);
    }

    @Test
    public void remove1() {
        assertEquals(entity.remove(source.testAccounts[5].getNumber()), source.testAccounts[5]);
        assertEquals(entity.get(0), source.testAccounts[6]);
        assertEquals(entity.get(1), source.testAccounts[7]);
        assertEquals(entity.get(2), source.testAccounts[8]);
    }

    @Test
    public void remove2() {
        assertTrue(entity.remove(source.testAccounts[5]));
        assertEquals(entity.get(0), source.testAccounts[6]);
        assertEquals(entity.get(1), source.testAccounts[7]);
        assertEquals(entity.get(2), source.testAccounts[8]);
    }

    @Test
    public void debtTotal() {
        double debBalance = 0;
        for (Account it : source.clients[1].getAccounts()) {
            if (it instanceof CreditAccount) {
                debBalance += it.getBalance();
            }
        }
        assertEquals(debBalance, entity.debtTotal(), 0.0);
    }

    @Test
    public void size() {
        assertEquals(source.clients[1].size(), entity.size());
    }

    @Test
    public void totalBalance() {
        double debBalance = 0;
        for (Account it : source.clients[1].getAccounts()) {
            debBalance += it.getBalance();
        }
        assertEquals(debBalance, entity.totalBalance(), 0.0);
    }

    @Test
    public void iteratorBLAD() {
        int k = 0;
        for (Account anIndividual : entity) {
            assertEquals(anIndividual, source.clients[1].get(k++));
        }
    }
}