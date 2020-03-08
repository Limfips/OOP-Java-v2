package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.util.Iterator;

import static org.junit.Assert.*;

public class IndividualTest {

    private Individual individual;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        individual = (Individual) source.clients[0];
    }

    @Test
    public void add() {
        try {
            assertTrue(individual.add(source.testAccounts[20]));
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add1() {
        try {
            assertTrue(individual.add(0, source.testAccounts[20]));
            assertEquals(individual.get(0), source.testAccounts[20]);
            assertEquals(individual.get(1), source.testAccounts[0]);
            assertEquals(individual.get(2), source.testAccounts[1]);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get() {
        assertEquals(individual.get(source.testAccounts[0].getNumber()), source.testAccounts[0]);
    }

    @Test
    public void get1() {
        assertEquals(individual.get(0), source.testAccounts[0]);
    }

    @Test
    public void hasAccount() {
        assertTrue(individual.hasAccount(source.testAccounts[0].getNumber()));
    }

    @Test
    public void set() {
        try {
            assertEquals(individual.set(0, source.testAccounts[20]), source.testAccounts[0]);
            assertEquals(individual.get(0), source.testAccounts[20]);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void remove() {
        assertEquals(individual.remove(0), source.testAccounts[0]);
        assertEquals(individual.get(0), source.testAccounts[1]);
        assertEquals(individual.get(1), source.testAccounts[2]);
        assertEquals(individual.get(2), source.testAccounts[3]);
    }

    @Test
    public void remove1() {
        assertEquals(individual.remove(source.testAccounts[0].getNumber()), source.testAccounts[0]);
        assertEquals(individual.get(0), source.testAccounts[1]);
        assertEquals(individual.get(1), source.testAccounts[2]);
        assertEquals(individual.get(2), source.testAccounts[3]);
    }

    @Test
    public void remove2() {
        assertTrue(individual.remove(source.testAccounts[0]));
        assertEquals(individual.get(0), source.testAccounts[1]);
        assertEquals(individual.get(1), source.testAccounts[2]);
        assertEquals(individual.get(2), source.testAccounts[3]);
    }

    @Test
    public void debtTotal() {
        double debBalance = 0;
        for (Account it : source.clients[0].getAccounts()) {
            if (it instanceof CreditAccount) {
                debBalance += it.getBalance();
            }
        }
        assertEquals(debBalance, individual.debtTotal(), 0.0);
    }

    @Test
    public void size() {
        assertEquals(source.clients[0].size(), individual.size());
    }

    @Test
    public void totalBalance() {
        double debBalance = 0;
        for (Account it : source.clients[0].getAccounts()) {
            debBalance += it.getBalance();
        }
        assertEquals(debBalance, individual.totalBalance(), 0.0);
    }

    @Test
    public void iteratorBLAD() {
        int k = 0;
        for (Account anIndividual : individual) {
            assertEquals(anIndividual, source.clients[0].get(k++));
        }
    }
}