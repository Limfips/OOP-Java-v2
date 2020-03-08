package rpis81.dudka.oop.model.source;

import rpis81.dudka.oop.model.*;

import java.time.LocalDate;

public class DataSource {

    public final String[] testNumbers = new String[]{
            "44547"+"810"+"5"+"3462"+"3460235",
            "44447"+"810"+"5"+"3462"+"3460235",
            "44047"+"810"+"5"+"3462"+"3460235",
            "44747"+"810"+"5"+"3462"+"3460235",
            "234545"+"810"+"5"+"3462"+"3460235",
            "44534545"+"823410"+"35"+"34362"+"3423460235",
            "", null};
    public final double[] testDebitBalance = new double[]{1241231, 123534654, 2346320, 235346325, 23532523};
    public final double[] testCreateBalance = new double[]{-1241231, -123534654, -2346320, -235346325, -23532523};
    public final LocalDate testExpirationDate = LocalDate.of(2020, 7, 5);
    public final LocalDate testCreationDate = LocalDate.of(2020, 2, 5);
    public final LocalDate newTestExpirationDate = LocalDate.of(2020, 11, 5);
    public final LocalDate expirationDate = LocalDate.of(2020, 10, 5);

    public final int testSizeAccounts = 25;
    public final int testSizeClients = 5;
    public final Account[] testAccounts = getTestAccounts();
    public final Client[] clients = getTestClients();


    private Account[] getTestAccounts() {
        Account[] testAccounts = new Account[testSizeAccounts];
        testAccounts[0] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000001", 34324,
                testCreationDate, testExpirationDate);
        testAccounts[1] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000002", 25435,
                testCreationDate, testExpirationDate);
        testAccounts[2] = new CreditAccount("44545"+"810"+"5"+"3462"+"0000003", -85934,
                9, testCreationDate, testExpirationDate);
        testAccounts[3] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000004", 12324,
                testCreationDate, testExpirationDate);
        testAccounts[4] = new CreditAccount("45545"+"810"+"5"+"3462"+"0000005", -56765,
                10, testCreationDate, testExpirationDate);
        testAccounts[5] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000006", 56856,
                testCreationDate, testExpirationDate);
        testAccounts[6] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000007", 34324,
                testCreationDate, testExpirationDate);
        testAccounts[7] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000008", 25435,
                testCreationDate, testExpirationDate);
        testAccounts[8] = new CreditAccount("45545"+"810"+"5"+"3462"+"0000009", -85934,
                12, testCreationDate, testExpirationDate);
        testAccounts[9] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000010", 12324,
                testCreationDate, testExpirationDate);
        testAccounts[10] = new CreditAccount("44545"+"810"+"5"+"3462"+"0000011", -56765,
                6, testCreationDate, testExpirationDate);
        testAccounts[11] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000012", 56856,
                testCreationDate, testExpirationDate);
        testAccounts[12] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000013", 34324,
                testCreationDate, testExpirationDate);
        testAccounts[13] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000014", 25435,
                testCreationDate, testExpirationDate);
        testAccounts[14] = new CreditAccount("45545"+"810"+"5"+"3462"+"0000015", -85934,
                9, testCreationDate, testExpirationDate);
        testAccounts[15] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000016", 12324,
                testCreationDate, testExpirationDate);
        testAccounts[16] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000017", 56765,
                testCreationDate, testExpirationDate);
        testAccounts[17] = new CreditAccount("44545"+"810"+"5"+"3462"+"0000018", -56856,
                13, testCreationDate, testExpirationDate);
        testAccounts[18] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000019", 34324,
                testCreationDate, testExpirationDate);
        testAccounts[19] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000020", 25435,
                testCreationDate, testExpirationDate);
        testAccounts[20] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000021", 85934,
                testCreationDate, testExpirationDate);
        testAccounts[21] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000022", 12324,
                testCreationDate, testExpirationDate);
        testAccounts[22] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000023", 56765,
                testCreationDate, testExpirationDate);
        testAccounts[23] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000024", 56856,
                testCreationDate, testExpirationDate);
        testAccounts[24] = new DebitAccount("40545"+"810"+"5"+"3462"+"0000025", 56853,
                testCreationDate, testExpirationDate);
        return testAccounts;
    }

    private Client[] getTestClients() {
        Account[] accounts = getTestAccounts();
        Client[] testClients = new Client[testSizeClients];
        Account[] tmpAccounts = new Account[5];
        System.arraycopy(accounts, 0, tmpAccounts, 0, 5);
        testClients[0] = new Individual("Individual_1", tmpAccounts);
        System.arraycopy(accounts, 5, tmpAccounts, 0, 5);
        try {
            testClients[1] = new Entity("Entity_2", tmpAccounts);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
        testClients[1].addCreditScores(-6);
        System.arraycopy(accounts, 10, tmpAccounts, 0, 5);
        testClients[2] = new Individual("Individual_3", tmpAccounts);
        testClients[2].addCreditScores(-2);
        System.arraycopy(accounts, 15, tmpAccounts, 0, 5);
        try {
            testClients[3] = new Entity("Entity_4", tmpAccounts);
        } catch (DublicateAccountNumberException e) {
            e.printStackTrace();
        }
        System.arraycopy(accounts, 20, tmpAccounts, 0, 5);
        testClients[4] = new Individual("Individual_5", tmpAccounts);
        return testClients;
    }
}
