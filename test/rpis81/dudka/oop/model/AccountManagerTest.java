package rpis81.dudka.oop.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {

    private AccountManager accountManager;
    private final Client[] clients = getTestClients();

    @Before
    public void init() {
        accountManager = new AccountManager(clients);
    }

    @Test
    public void add() {
        Client clientF = new Entity("01");
        assertTrue(accountManager.add(clientF));
        Client clientS = new Individual("02");
        assertTrue(accountManager.add(clientS));
        assertEquals(clients.length + 2, accountManager.size());
        assertEquals(accountManager.get(clients.length - 1), clients[clients.length-1]);
        assertEquals(accountManager.get(clients.length), clientF);
        assertEquals(accountManager.get(clients.length + 1), clientS);
    }

    @Test
    public void add1() {
        Client clientF = new Entity("01");
        assertFalse(accountManager.add(-1, clientF));
        assertFalse(accountManager.add(2132, clientF));
        assertTrue(accountManager.add(0, clientF));
        Client clientS = new Individual("02");
        assertTrue(accountManager.add(2, clientS));
        assertEquals(clients.length + 2, accountManager.size());
        assertEquals(accountManager.get(0), clientF);
        assertEquals(accountManager.get(1), clients[0]);
        assertEquals(accountManager.get(2), clientS);
        assertEquals(accountManager.get(3), clients[1]);
        assertEquals(accountManager.get(4), clients[2]);
        assertEquals(accountManager.get(5), clients[3]);
        assertEquals(accountManager.get(6), clients[4]);
    }

    @Test
    public void get() {
        assertEquals(accountManager.get(0), clients[0]);
        assertEquals(accountManager.get(1), clients[1]);
        assertEquals(accountManager.get(2), clients[2]);
    }

    @Test
    public void set() {
        Client clientF = new Entity("01");
        assertNull(accountManager.set(-1, clientF));
        assertNull(accountManager.set(2132, clientF));
        assertEquals(accountManager.set(0, clientF), clients[0]);
        assertEquals(accountManager.get(0), clientF);
        assertEquals(accountManager.get(1), clients[1]);
        assertEquals(accountManager.get(2), clients[2]);
        Client clientS = new Individual("02");
        assertEquals(accountManager.set(2, clientS), clients[2]);
        assertEquals(accountManager.get(0), clientF);
        assertEquals(accountManager.get(1), clients[1]);
        assertEquals(accountManager.get(2), clientS);
        assertEquals(clients.length, accountManager.size());
    }

    @Test
    public void remove() {
        assertNull(accountManager.remove(-1));
        assertNull(accountManager.remove(123));
        assertEquals(accountManager.remove(0), clients[0]);
        assertEquals(accountManager.remove(2), clients[3]);

        assertEquals(clients.length - 2, accountManager.size());
        assertEquals(accountManager.get(0), clients[1]);
        assertEquals(accountManager.get(1), clients[2]);
        assertEquals(accountManager.get(2), clients[4]);
    }

    @Test
    public void size() {
        assertEquals(clients.length, accountManager.size());
    }

    @Test
    public void getClients() {
        for (Client it : accountManager.getClients()) {
            assertNotNull(it);
        }
    }

    @Test
    public void sortedByBalanceClients() {
        double testBalanse = 0;
        Client[] clients = accountManager.sortedByBalanceClients();
        for (Client it : clients) {
            assertTrue(it.totalBalance() > testBalanse);
            testBalanse = it.totalBalance();
        }
    }

    @Test
    public void getClientsWithOneCredit() {
        Client[] clients = accountManager.getClientsWithOneCredit();
        assertEquals(clients.length, 4);
    }

    @Test
    public void getBedClientsWithOneCredit() {
        Client[] clients = accountManager.getBedClientsWithOneCredit();
        assertEquals(clients.length, 1);
    }

    private Account[] getTestAccounts() {
        Account[] testAccounts = new Account[25];
        testAccounts[0] = new DebitAccount("001", 34324);
        testAccounts[1] = new DebitAccount("002", 25435);
        testAccounts[2] = new CreditAccount("003", 85934, 9);
        testAccounts[3] = new DebitAccount("004", 12324);
        testAccounts[4] = new CreditAccount("005", 56765, 10);
        testAccounts[5] = new DebitAccount("006", 56856);
        testAccounts[6] = new DebitAccount("007", 34324);
        testAccounts[7] = new DebitAccount("008", 25435);
        testAccounts[8] = new CreditAccount("009", 85934, 12);
        testAccounts[9] = new DebitAccount("010", 12324);
        testAccounts[10] = new CreditAccount("011", 56765, 6);
        testAccounts[11] = new DebitAccount("012", 56856);
        testAccounts[12] = new DebitAccount("013", 34324);
        testAccounts[13] = new DebitAccount("014", 25435);
        testAccounts[14] = new CreditAccount("015", 85934, 9);
        testAccounts[15] = new DebitAccount("016", 12324);
        testAccounts[16] = new DebitAccount("017", 56765);
        testAccounts[17] = new CreditAccount("018", 56856, 13);
        testAccounts[18] = new DebitAccount("019", 34324);
        testAccounts[19] = new DebitAccount("020", 25435);
        testAccounts[20] = new DebitAccount("021", 85934);
        testAccounts[21] = new DebitAccount("022", 12324);
        testAccounts[22] = new DebitAccount("023", 56765);
        testAccounts[23] = new DebitAccount("024", 56856);
        testAccounts[24] = new DebitAccount("025", 56853);
        return testAccounts;
    }

    private Client[] getTestClients() {
        Account[] accounts = getTestAccounts();
        Client[] testClients = new Client[5];
        Account[] tmpAccounts = new Account[5];
        System.arraycopy(accounts, 0, tmpAccounts, 0, 5);
        testClients[0] = new Individual("Individual_1", tmpAccounts);
        System.arraycopy(accounts, 5, tmpAccounts, 0, 5);
        testClients[1] = new Entity("Entity_2", tmpAccounts);
        testClients[1].addCreditScores(-6);
        System.arraycopy(accounts, 10, tmpAccounts, 0, 5);
        testClients[2] = new Individual("Individual_3", tmpAccounts);
        testClients[2].addCreditScores(-2);
        System.arraycopy(accounts, 15, tmpAccounts, 0, 5);
        testClients[3] = new Entity("Entity_4", tmpAccounts);
        System.arraycopy(accounts, 20, tmpAccounts, 0, 5);
        testClients[4] = new Individual("Individual_5", tmpAccounts);
        return testClients;
    }
}