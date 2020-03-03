package rpis81.dudka.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class IndividualTest {

    private final String testName = "TestNameIndividual";
    private final int testSize = 8;
    private final Account[] firstTestAccounts = getTestAccounts();

    @Test
    public void initFirstConstructor() {
        Individual individual = new Individual(testName);
        assertEquals(individual.getName(), testName);
        assertEquals(individual.size(), 0);
        assertEquals(individual.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT);
    }

    @Test
    public void initSecondConstructor() {
        Individual individual = new Individual(testName, testSize);
        assertEquals(individual.getName(), testName);
        assertEquals(individual.size(), 0);
        assertEquals(individual.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT);
    }

    @Test
    public void initThirdConstructor() {
        Individual individual = new Individual(testName, firstTestAccounts);
        assertEquals(individual.getName(), testName);
        assertEquals(individual.size(), 25);
        assertEquals(individual.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT);
    }

    @Test
    public void add() {
        Individual individual = new Individual(testName, testSize);
        assertEquals(individual.size(), 0);
        assertTrue(individual.add(firstTestAccounts[0]));
        assertEquals(individual.get(0), firstTestAccounts[0]);
        assertEquals(individual.size(), 1);
        assertTrue(individual.add(firstTestAccounts[1]));
        assertEquals(individual.get(1), firstTestAccounts[1]);
        assertEquals(individual.size(), 2);
        assertTrue(individual.add(firstTestAccounts[2]));
        assertEquals(individual.get(2), firstTestAccounts[2]);
        assertEquals(individual.size(), 3);
    }

    @Test
    public void add1() {
        Account[] accounts = new Account[5];
        int testSizeIndividual = 5;
        System.arraycopy(firstTestAccounts, 0, accounts, 0, 5);
        Individual individual = new Individual(testName, accounts);
        assertEquals(individual.size(), testSizeIndividual);
        Account[] individualAccounts = individual.getAccounts();
        for (int i = 0; i < individual.size(); i++) {
            assertNotNull(individualAccounts[i]);
            assertEquals(individualAccounts[i], accounts[i]);
        }
        assertFalse(individual.add(-1, firstTestAccounts[10]));
        assertEquals(individual.size(), testSizeIndividual);
        assertFalse(individual.add(100000, firstTestAccounts[10]));
        assertEquals(individual.size(), testSizeIndividual);
        assertTrue(individual.add(0, firstTestAccounts[10]));
        testSizeIndividual += 1;
        assertEquals(individual.size(), testSizeIndividual);
        assertEquals(individual.get(0), firstTestAccounts[10]);
        assertEquals(individual.get(1), firstTestAccounts[0]);
        assertEquals(individual.get(2), firstTestAccounts[1]);
        assertEquals(individual.get(3), firstTestAccounts[2]);
        assertTrue(individual.add(2, firstTestAccounts[11]));
        testSizeIndividual += 1;
        assertEquals(individual.size(), testSizeIndividual);
        assertEquals(individual.get(0), firstTestAccounts[10]);
        assertEquals(individual.get(1), firstTestAccounts[0]);
        assertEquals(individual.get(2), firstTestAccounts[11]);
        assertEquals(individual.get(3), firstTestAccounts[1]);
        assertEquals(individual.get(4), firstTestAccounts[2]);
        assertEquals(individual.get(5), firstTestAccounts[3]);
    }

    @Test
    public void get() {
        Individual individual = new Individual(testName, firstTestAccounts);
        assertEquals(individual.get(firstTestAccounts[8].getNumber()), firstTestAccounts[8]);
    }

    @Test
    public void hasAccount() {
        Individual individual = new Individual(testName, firstTestAccounts);
        assertTrue(individual.hasAccount(firstTestAccounts[8].getNumber()));
        assertFalse(individual.hasAccount("asdasdasdasd"));
    }

    @Test
    public void set() {
        Account[] accounts = new Account[5];
        int testSizeIndividual = 5;
        System.arraycopy(firstTestAccounts, 0, accounts, 0, 5);
        Individual individual = new Individual(testName, accounts);
        assertEquals(individual.size(), testSizeIndividual);

        assertNull(individual.set(-1, firstTestAccounts[0]));
        assertNull(individual.set(10000, firstTestAccounts[0]));
        assertEquals(individual.set(0, firstTestAccounts[7]), firstTestAccounts[0]);
        assertEquals(individual.set(3, firstTestAccounts[8]), firstTestAccounts[3]);

        assertEquals(individual.get(0), firstTestAccounts[7]);
        assertEquals(individual.get(1), firstTestAccounts[1]);
        assertEquals(individual.get(2), firstTestAccounts[2]);
        assertEquals(individual.get(3), firstTestAccounts[8]);
        assertEquals(individual.get(4), firstTestAccounts[4]);
    }

    @Test
    public void remove() {
        int testSizeIndividual = 10;
        Account[] accounts = new Account[testSizeIndividual];
        System.arraycopy(firstTestAccounts, 0, accounts, 0, testSizeIndividual);
        Individual individual = new Individual(testName, accounts);
        assertEquals(individual.size(), testSizeIndividual);

        for (int i = 0; i < testSizeIndividual; i++) {
            assertEquals(individual.get(i), accounts[i]);
        }

        assertNull(individual.remove("asdasdasd"));
        assertNull(individual.remove(firstTestAccounts[24].getNumber()));
        assertEquals(individual.remove(firstTestAccounts[0].getNumber()), firstTestAccounts[0]);
        assertEquals(individual.remove(firstTestAccounts[1].getNumber()), firstTestAccounts[1]);
        assertEquals(individual.remove(firstTestAccounts[6].getNumber()), firstTestAccounts[6]);

        assertEquals(testSizeIndividual - 3, individual.size());

        assertEquals(individual.get(0), firstTestAccounts[2]);
        assertEquals(individual.get(1), firstTestAccounts[3]);
        assertEquals(individual.get(2), firstTestAccounts[4]);
        assertEquals(individual.get(3), firstTestAccounts[5]);
        assertEquals(individual.get(4), firstTestAccounts[7]);
        assertEquals(individual.get(5), firstTestAccounts[8]);
        assertEquals(individual.get(6), firstTestAccounts[9]);
    }

    @Test
    public void remove1() {
        int testSizeIndividual = 10;
        Account[] accounts = new Account[testSizeIndividual];
        System.arraycopy(firstTestAccounts, 0, accounts, 0, testSizeIndividual);
        Individual individual = new Individual(testName, accounts);
        assertEquals(individual.size(), testSizeIndividual);

        for (int i = 0; i < testSizeIndividual; i++) {
            assertEquals(individual.get(i), accounts[i]);
        }

        assertNull(individual.remove(-1));
        assertNull(individual.remove(100000));
        assertEquals(individual.remove(0), firstTestAccounts[0]);
        assertEquals(individual.remove(1), firstTestAccounts[2]);
        assertEquals(individual.remove(6), firstTestAccounts[8]);

        assertEquals(testSizeIndividual - 3, individual.size());

        assertEquals(individual.get(0), firstTestAccounts[1]);
        assertEquals(individual.get(1), firstTestAccounts[3]);
        assertEquals(individual.get(2), firstTestAccounts[4]);
        assertEquals(individual.get(3), firstTestAccounts[5]);
        assertEquals(individual.get(4), firstTestAccounts[6]);
        assertEquals(individual.get(5), firstTestAccounts[7]);
        assertEquals(individual.get(6), firstTestAccounts[9]);
    }

    @Test
    public void sortedAccountsByBalance() {
        Individual individual = new Individual(testName, firstTestAccounts);
        Account[] accounts;
        accounts = individual.sortedAccountsByBalance();
        assertNotNull(accounts);
        assertEquals(firstTestAccounts.length, accounts.length);
        double balance = 0;
        for (Account it : accounts) {
            assertTrue(it.getBalance() >= balance);
            balance = it.getBalance();
        }
    }

    @Test
    public void totalBalance() {
        Individual individual = new Individual(testName, firstTestAccounts);
        double total = 0;
        for (Account it : firstTestAccounts) {
            total += it.getBalance();
        }
        assertEquals(total, individual.totalBalance(), 0.0);
    }

    @Test
    public void getIndex() {
        Individual individual = new Individual(testName, firstTestAccounts);
        assertEquals(-1, individual.getIndex("sdsafsd"));
        assertEquals(0, individual.getIndex(firstTestAccounts[0].getNumber()));
        assertEquals(4, individual.getIndex(firstTestAccounts[4].getNumber()));
    }

    @Test
    public void getCreditScores() {
        Individual individual = new Individual(testName, firstTestAccounts);
        assertEquals(individual.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT);
    }

    @Test
    public void addCreditScores() {
        Individual individual = new Individual("asd");
        individual.addCreditScores(10);
        assertEquals(individual.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT + 10);
    }

    @Test
    public void getCreditAccounts() {
        Account[] accounts = new Account[10];
        accounts[0] = new CreditAccount("CreditAccount_1", 812422, 12);
        accounts[1] = firstTestAccounts[0];
        accounts[2] = firstTestAccounts[1];
        accounts[3] = new CreditAccount("CreditAccount_2", 345352, 9);
        accounts[4] = firstTestAccounts[2];
        accounts[5] = firstTestAccounts[3];
        accounts[6] = new CreditAccount("CreditAccount_3", 345344, 11);
        accounts[7] = firstTestAccounts[4];
        accounts[8] = firstTestAccounts[5];
        accounts[9] = new CreditAccount("CreditAccount_4", 732433, 7);
        Individual individual = new Individual("asd", accounts);

        assertEquals(individual.getCreditAccounts().length, 4);
    }

    @Test
    public void getStatus() {
        Individual individual = new Individual("asd");
        assertEquals(individual.getStatus(), ClientStatus.GOOD);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.GOOD);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.GOOD);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.GOLD);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.GOLD);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.PLATINUM);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.PLATINUM);
        individual.addCreditScores(1);
        assertEquals(individual.getStatus(), ClientStatus.PLATINUM);

        individual.addCreditScores(-8);
        assertEquals(individual.getStatus(), ClientStatus.RISKY);
        individual.addCreditScores(-1);
        assertEquals(individual.getStatus(), ClientStatus.RISKY);
        individual.addCreditScores(-1);
        assertEquals(individual.getStatus(), ClientStatus.BAD);
        individual.addCreditScores(-1);
        assertEquals(individual.getStatus(), ClientStatus.BAD);
        individual.addCreditScores(-1);
        assertEquals(individual.getStatus(), ClientStatus.BAD);
        individual.addCreditScores(-1);
        assertEquals(individual.getStatus(), ClientStatus.BAD);
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
}