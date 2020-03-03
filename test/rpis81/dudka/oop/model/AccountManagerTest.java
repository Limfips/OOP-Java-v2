package rpis81.dudka.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountManagerTest {

    @Test
    public void add() {
    }

    @Test
    public void add1() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void size() {
    }

    @Test
    public void getClients() {
    }

    @Test
    public void sortedByBalanceClients() {
    }

    @Test
    public void getAccount() {
    }

    @Test
    public void removeAccount() {
    }

    @Test
    public void setAccount() {
    }

    @Test
    public void getClientsWithOneCredit() {

    }

    @Test
    public void getBedClientsWithOneCredit() {

    }

    private Account[] getTestAccounts() {
        Account[] testAccounts = new Account[25];
        testAccounts[0] = new DebitAccount("001", 34324);
        testAccounts[1] = new DebitAccount("002", 25435);
        testAccounts[2] = new DebitAccount("003", 85934);
        testAccounts[3] = new DebitAccount("004", 12324);
        testAccounts[4] = new DebitAccount("005", 56765);
        testAccounts[5] = new DebitAccount("006", 56856);
        testAccounts[6] = new DebitAccount("007", 34324);
        testAccounts[7] = new DebitAccount("008", 25435);
        testAccounts[8] = new DebitAccount("009", 85934);
        testAccounts[9] = new DebitAccount("010", 12324);
        testAccounts[10] = new DebitAccount("011", 56765);
        testAccounts[11] = new DebitAccount("012", 56856);
        testAccounts[12] = new DebitAccount("013", 34324);
        testAccounts[13] = new DebitAccount("014", 25435);
        testAccounts[14] = new DebitAccount("015", 85934);
        testAccounts[15] = new DebitAccount("016", 12324);
        testAccounts[16] = new DebitAccount("017", 56765);
        testAccounts[17] = new DebitAccount("018", 56856);
        testAccounts[18] = new DebitAccount("019", 34324);
        testAccounts[19] = new DebitAccount("020", 25435);
        testAccounts[20] = new DebitAccount("021", 85934);
        testAccounts[21] = new DebitAccount("022", 12324);
        testAccounts[22] = new DebitAccount("023", 56765);
        testAccounts[23] = new DebitAccount("024", 56856);
        testAccounts[24] = new DebitAccount("025", 56856);
        return testAccounts;
    }

    private Individual[] getTestIndividuals() {
        Account[] accounts = getTestAccounts();
        Individual[] testIndividuals = new Individual[5];
        Individual tmpIndividual;
        Account[] tmpAccounts = new Account[5];
        for (int i = 0, j = 0, k = 0; i < accounts.length; i++) {
            if (k == 4) {
                tmpAccounts[k] = accounts[i];
                tmpIndividual = new Individual("testName", tmpAccounts);
                testIndividuals[j++] = tmpIndividual;
                k = 0;
            } else {
                tmpAccounts[k++] = accounts[i];
            }
        }
        return testIndividuals;
    }
}