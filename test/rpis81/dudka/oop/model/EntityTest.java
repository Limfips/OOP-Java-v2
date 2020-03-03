package rpis81.dudka.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest {

    private static final int testSize = 8;
    private final Account[] firstTestAccounts = getFirstTestAccounts();
    private final Account[] secondTestAccounts = getSecondTestAccounts();

    private Entity getFirstConstructor() {
        String firstName = "FirstEntity";
        return new Entity(firstName);
    }

    private Entity getSecondConstructor() {
        String secondName = "SecondEntity";
        return new Entity(secondName, firstTestAccounts);
    }

    private Account[] getFirstTestAccounts() {
        Account[] accounts = new Account[testSize];
        accounts[0] = new DebitAccount("Account_1", 13211);
        accounts[1] = new DebitAccount("Account_2", 23534);
        accounts[2] = new DebitAccount("Account_3", 34664);
        accounts[3] = new DebitAccount("Account_4", 45664);
        accounts[4] = new DebitAccount("Account_5", 12342);
        accounts[5] = new DebitAccount("Account_6", 37546);
        accounts[6] = new DebitAccount("Account_7", 54745);
        accounts[7] = new DebitAccount("Account_8", 21321);
        return accounts;
    }

    private Account[] getSecondTestAccounts() {
        Account[] accounts = new Account[testSize];
        accounts[0] = new DebitAccount("Account_9", 568567);
        accounts[1] = new DebitAccount("Account_10", 465634);
        accounts[2] = new DebitAccount("Account_11", 235334);
        accounts[3] = new DebitAccount("Account_12", 364334);
        accounts[4] = new DebitAccount("Account_13", 165752);
        accounts[5] = new DebitAccount("Account_14", 168752);
        accounts[6] = new DebitAccount("Account_15", 234234);
        accounts[7] = new DebitAccount("Account_16", 241321);
        return accounts;
    }
    
    @Test
    public void add() {
        Entity entity;

        entity = getFirstConstructor();
        assertTrue(entity.add(firstTestAccounts[0]));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), firstTestAccounts[0]);
        assertTrue(entity.add(firstTestAccounts[1]));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), firstTestAccounts[1]);
        assertTrue(entity.add(firstTestAccounts[2]));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), firstTestAccounts[2]);

        entity = getSecondConstructor();
        assertTrue(entity.add(secondTestAccounts[0]));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), secondTestAccounts[0]);
        assertTrue(entity.add(secondTestAccounts[1]));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), secondTestAccounts[1]);
    }

    @Test
    public void add1() {
        Entity entity;

        entity = getFirstConstructor();
        assertFalse(entity.add(-1, firstTestAccounts[0]));
        assertFalse(entity.add(1000, firstTestAccounts[1]));
        assertFalse(entity.add(1, firstTestAccounts[2]));
        assertFalse(entity.add(0, firstTestAccounts[3]));

        entity = getSecondConstructor();
        assertFalse(entity.add(-1, secondTestAccounts[0]));
        assertFalse(entity.add(1000, secondTestAccounts[1]));
        assertTrue(entity.add(1, secondTestAccounts[3]));
        assertEquals(entity.get(1), secondTestAccounts[3]);
        assertTrue(entity.add(0, secondTestAccounts[4]));
        assertEquals(entity.getFirst(), secondTestAccounts[4]);
        assertEquals(entity.getLast(), firstTestAccounts[7]);
        assertEquals(entity.get(1), firstTestAccounts[0]);
        assertEquals(entity.get(2), secondTestAccounts[3]);
        assertTrue(entity.add(entity.size() - 1, secondTestAccounts[6]));
        assertEquals(entity.get(entity.size() - 2), secondTestAccounts[6]);
    }

    @Test
    public void get() {
        Entity entity;

        entity = getFirstConstructor();
        assertNull(entity.get(0));
        assertNull(entity.get(1));
        assertNull(entity.get(-1));
        assertNull(entity.get(100));

        entity = getSecondConstructor();

        assertEquals(entity.get(0), firstTestAccounts[0]);
        assertEquals(entity.get(1), firstTestAccounts[1]);
        assertEquals(entity.get(testSize - 1), firstTestAccounts[testSize - 1]);
        assertNull(entity.get(-1));
        assertNull(entity.get(100));
    }

    @Test
    public void get1() {
        Entity entity;

        entity = getFirstConstructor();
        assertNull(entity.get(firstTestAccounts[0].getNumber()));
        assertNull(entity.get(firstTestAccounts[1].getNumber()));
        assertNull(entity.get("asdasdasd"));
        assertNull(entity.get(firstTestAccounts[7].getNumber()));

        entity = getSecondConstructor();

        assertEquals(entity.get(firstTestAccounts[0].getNumber()), firstTestAccounts[0]);
        assertEquals(entity.get(firstTestAccounts[1].getNumber()), firstTestAccounts[1]);
        assertEquals(entity.get(firstTestAccounts[testSize - 1].getNumber()), firstTestAccounts[testSize - 1]);
        assertNull(entity.get("asfasfasfasf"));
        assertNull(entity.get("sdhbfadjk;fasf"));
    }

    @Test
    public void hasAccount() {
        Entity entity;

        entity = getFirstConstructor();
        assertFalse(entity.hasAccount(firstTestAccounts[0].getNumber()));
        assertFalse(entity.hasAccount(firstTestAccounts[1].getNumber()));
        assertFalse(entity.hasAccount("asdasdasd"));
        assertFalse(entity.hasAccount(firstTestAccounts[7].getNumber()));

        entity = getSecondConstructor();

        assertTrue(entity.hasAccount(firstTestAccounts[0].getNumber()));
        assertTrue(entity.hasAccount(firstTestAccounts[1].getNumber()));
        assertTrue(entity.hasAccount(firstTestAccounts[testSize - 1].getNumber()));
        assertFalse(entity.hasAccount("asfasfasfasf"));
        assertFalse(entity.hasAccount("sdhbfadjk;fasf"));
    }

    @Test
    public void set() {
        Entity entity;

        entity = getFirstConstructor();
        assertNull(entity.set(-1, firstTestAccounts[0]));
        assertNull(entity.set(0, firstTestAccounts[0]));
        assertNull(entity.set(1, firstTestAccounts[0]));
        assertNull(entity.set(1000, firstTestAccounts[0]));
        assertNull(entity.set(-1, null));
        assertNull(entity.set(0, null));
        assertNull(entity.set(1, null));
        assertNull(entity.set(1000, null));

        entity = getSecondConstructor();
        assertNull(entity.set(-1, firstTestAccounts[0]));
        assertEquals(entity.set(0, secondTestAccounts[0]), firstTestAccounts[0]);
        assertEquals(entity.get(0), secondTestAccounts[0]);
        assertEquals(entity.set(1, secondTestAccounts[1]), firstTestAccounts[1]);
        assertEquals(entity.get(1), secondTestAccounts[1]);
        assertEquals(entity.set(5, secondTestAccounts[5]), firstTestAccounts[5]);
        assertEquals(entity.get(5), secondTestAccounts[5]);
        assertNull(entity.set(1000, firstTestAccounts[7]));
        assertNull(entity.set(-1, null));
        assertNull(entity.set(0, null));
        assertNull(entity.set(1, null));
        assertNull(entity.set(1000, null));

    }

    @Test
    public void remove() {
        Entity entity;

        entity = getFirstConstructor();
        assertNull(entity.remove(""));
        assertNull(entity.remove("gdsgsgerg"));

        entity = getSecondConstructor();
        assertNull(entity.remove(""));
        assertNull(entity.remove("gdsgsgerg"));
        assertEquals(entity.remove(firstTestAccounts[0].getNumber()), firstTestAccounts[0]);
        assertEquals(entity.get(0), firstTestAccounts[1]);
        assertEquals(entity.remove(firstTestAccounts[2].getNumber()), firstTestAccounts[2]);
        assertEquals(entity.get(1), firstTestAccounts[3]);
        assertEquals(entity.remove(firstTestAccounts[4].getNumber()), firstTestAccounts[4]);
        assertEquals(entity.get(2), firstTestAccounts[5]);
    }

    @Test
    public void size() {
        Entity entity;

        entity = getFirstConstructor();
        assertEquals(0, entity.size());
        assertEquals(entity.getFirst(), (entity.getLast()));

        entity = getSecondConstructor();
        assertEquals(testSize, entity.size());
        assertNotEquals(entity.getFirst(), (entity.getLast()));
        assertEquals(entity.getFirst(), firstTestAccounts[0]);
        assertEquals(entity.getLast(), firstTestAccounts[testSize-1]);
    }

    @Test
    public void getAccounts() {
        Entity entity;

        entity = getFirstConstructor();
        assertTrue(entity.isEmpty());

        entity = getSecondConstructor();
        assertFalse(entity.isEmpty());
        assertNotNull(entity.getAccounts());
    }

    @Test
    public void sortedAccountsByBalance() {
        Entity entity;
        Account[] accounts;

        entity = getFirstConstructor();
        accounts = entity.sortedAccountsByBalance();
        assertNotNull(accounts);
        assertEquals(0, accounts.length);

        entity = getSecondConstructor();
        accounts = entity.sortedAccountsByBalance();
        assertNotNull(accounts);
        assertEquals(testSize, accounts.length);
        double balance = 0;
        for (Account it : accounts) {
            assertTrue(it.getBalance() >= balance);
            balance = it.getBalance();
        }
    }

    @Test
    public void totalBalance() {
        Entity entity;

        entity = getFirstConstructor();
        assertEquals(0, entity.totalBalance(), 0.0);

        entity = getSecondConstructor();
        double total = 0;
        for (Account it : firstTestAccounts) {
            total += it.getBalance();
        }
        assertEquals(total, entity.totalBalance(), 0.0);
    }

    @Test
    public void getIndex() {
        Entity entity;

        entity = getFirstConstructor();
        assertEquals(-1, entity.getIndex("sdsafsd"));

        entity = getSecondConstructor();
        assertEquals(-1, entity.getIndex("sdsafsd"));
        assertEquals(0, entity.getIndex(firstTestAccounts[0].getNumber()));
        assertEquals(4, entity.getIndex(firstTestAccounts[4].getNumber()));
    }

    @Test
    public void getCreditScores() {
        Entity entity;

        entity = getFirstConstructor();
        assertEquals(entity.getCreditScores(), Entity.CREDIT_SCORES_DEFAULT);

        entity = getSecondConstructor();
        assertEquals(entity.getCreditScores(), Entity.CREDIT_SCORES_DEFAULT);
    }

    @Test
    public void addCreditScores() {
        Entity entity = new Entity("sad");
        entity.addCreditScores(10);
        assertEquals(entity.getCreditScores(), Individual.CREDIT_SCORES_DEFAULT + 10);
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
        Entity entity = new Entity("asd", accounts);

        assertEquals(entity.getCreditAccounts().length, 4);
    }

    @Test
    public void getStatus() {
        Entity entity = new Entity("sad");
        assertEquals(entity.getStatus(), ClientStatus.GOOD);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.GOOD);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.GOOD);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.GOLD);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.GOLD);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.PLATINUM);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.PLATINUM);
        entity.addCreditScores(1);
        assertEquals(entity.getStatus(), ClientStatus.PLATINUM);

        entity.addCreditScores(-8);
        assertEquals(entity.getStatus(), ClientStatus.RISKY);
        entity.addCreditScores(-1);
        assertEquals(entity.getStatus(), ClientStatus.RISKY);
        entity.addCreditScores(-1);
        assertEquals(entity.getStatus(), ClientStatus.BAD);
        entity.addCreditScores(-1);
        assertEquals(entity.getStatus(), ClientStatus.BAD);
        entity.addCreditScores(-1);
        assertEquals(entity.getStatus(), ClientStatus.BAD);
        entity.addCreditScores(-1);
        assertEquals(entity.getStatus(), ClientStatus.BAD);
    }
}