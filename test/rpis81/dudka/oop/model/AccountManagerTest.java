package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;
import rpis81.dudka.oop.model.source.DataSource;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private DataSource source;

    @Before
    public void init() {
        source = new DataSource();
        accountManager = new AccountManager(new Client[]{source.clients[0],
                                    source.clients[1], source.clients[2]});
    }

    @Test
    public void add() {
        assertTrue(accountManager.add(source.clients[3]));
    }

    @Test
    public void add1() {
        assertTrue(accountManager.add(0, source.clients[3]));
        assertEquals(accountManager.get(0), source.clients[3]);
        assertEquals(accountManager.get(1), source.clients[0]);
        assertEquals(accountManager.get(2), source.clients[1]);
    }

    @Test
    public void set() {
        assertEquals(accountManager.set(0, source.clients[3]), source.clients[0]);
        assertEquals(accountManager.get(0), source.clients[3]);
        assertEquals(accountManager.get(1), source.clients[1]);
        assertEquals(accountManager.get(2), source.clients[2]);
    }

    @Test
    public void remove() {
        assertEquals(accountManager.remove(0), source.clients[0]);
        assertEquals(accountManager.get(0), source.clients[1]);
        assertEquals(accountManager.get(1), source.clients[2]);
    }

    @Test
    public void size() {
        assertEquals(3, accountManager.size());
    }

    @Test
    public void sortedByBalanceClients() {
        List<Client> clients = accountManager.sortedByBalanceClients();
        double balance = -10000000000000000000.0;
        for (Client it : clients) {
            assertTrue(it.totalBalance() >= balance);
            balance = it.totalBalance();
        }
    }

    @Test
    public void getAccount() {
        assertEquals(accountManager.getAccount(source.testAccounts[6].getNumber()),
                source.testAccounts[6]);
    }

    @Test
    public void removeAccount() {
        assertEquals(accountManager.removeAccount(
                source.testAccounts[6].getNumber()),
                source.testAccounts[6]);
    }

    @Test
    public void remove1() {
        assertTrue(accountManager.remove(source.clients[0]));
    }

    @Test
    public void setAccount() {
        try {
            assertEquals(accountManager.setAccount(source.testAccounts[7].getNumber(), source.testAccounts[20]),
                    source.testAccounts[7]);
        } catch (DuplicateAccountNumberException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getClientsWithOneCredit() {
        Set<Client> clients = accountManager.getClientsWithOneCredit();
        assertEquals(clients.size(), 3);
    }

    @Test
    public void getBedClientsWithOneCredit() {
        Set<Client> clients = accountManager.getBedClientsWithOneCredit();
        assertEquals(clients.size(), 1);
    }
}