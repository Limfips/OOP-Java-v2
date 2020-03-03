package rpis81.dudka.oop;

import rpis81.dudka.oop.model.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("Я сделять!");
        lab1tests();
        lab2tests();
    }
    private static void lab1tests() {
        //Тестирования класса Account ----------------------------------------------------------------------------------
        FirstTestAccount();
        SecondTestAccount();
        //--------------------------------------------------------------------------------------------------------------
        //Тестирования класса Individual -------------------------------------------------------------------------------
        FirstTestIndividual();
        SecondTestIndividual();
        ThirdTestIndividual();
        //--------------------------------------------------------------------------------------------------------------
        //Тестирования класса AccountManager ---------------------------------------------------------------------------
        FirstTestAccountManager();
        SecondTestAccountManager();
        //--------------------------------------------------------------------------------------------------------------
    }

    private static void lab2tests() {

    }

    private static void FirstTestAccount() {
        long start = System.currentTimeMillis();
        Account account = new DebitAccount();
        String oldNumberAccount = account.getNumber();
        double oldBalance = account.getBalance();
        String testNumberAccount = "142354534";
        double testBalance = 100000;
        account.setNumber(testNumberAccount);
        account.setBalance(testBalance);
        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if ((account.getNumber().equals(testNumberAccount) &&
                account.getBalance() == testBalance ) &&
                (oldNumberAccount.equals(DebitAccount.NUMBER_DEFAULT) &&
                        oldBalance == DebitAccount.BALANCE_DEFAULT)) {
            result.append("COMPLETED: First constructor account test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: First constructor account test.");
        }
    }

    private static void SecondTestAccount() {
        long start = System.currentTimeMillis();
        Account account = new DebitAccount("63643278492", 213);
        String oldNumberAccount = account.getNumber();
        double oldBalance = account.getBalance();
        String testNumberAccount = "142354534";
        double testBalance = 100000;
        account.setNumber(testNumberAccount);
        account.setBalance(testBalance);
        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if ((account.getNumber().equals(testNumberAccount) &&
                account.getBalance() == testBalance ) &&
                (!oldNumberAccount.equals(DebitAccount.NUMBER_DEFAULT) &&
                        oldBalance != DebitAccount.BALANCE_DEFAULT)) {
            result.append("COMPLETED: Second constructor account test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: Second constructor account test.");
        }
    }

    private static void FirstTestIndividual() {
        long start = System.currentTimeMillis();
        Individual individual = new Individual("testName");
        boolean test;
        int testSize = 0;
        Account[] testAccounts = getAccounts();
        test = testAccounts.length == 25;
        test = test && testSize == individual.size();

        test = test && individual.add(testAccounts[0]);
        testSize += 1;
        test = test && testSize == individual.size();
        test = test && individual.get(individual.size()-1).equals(testAccounts[0]);

        test = test && individual.add(testAccounts[1]);
        testSize += 1;
        test = test && individual.get(individual.size()-1).equals(testAccounts[1]);
        test = test && individual.add(testAccounts[2]);
        testSize += 1;
        test = test && individual.get(individual.size()-1).equals(testAccounts[2]);
        test = test && testSize == individual.size();

        test = test && !(individual.add(-1, testAccounts[3]));
        test = test && testSize == individual.size();
        test = test && !(individual.add(Individual.SIZE_DEFAULT + 1, testAccounts[3]));
        test = test && testSize == individual.size();
        test = test && !(individual.add(Individual.SIZE_DEFAULT, testAccounts[3]));
        test = test && testSize == individual.size();

        test = test && (individual.add(0, testAccounts[3]));
        testSize += 1;
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[3]);
        test = test && individual.get(1).equals(testAccounts[0]);
        test = test && individual.get(2).equals(testAccounts[1]);
        test = test && individual.get(3).equals(testAccounts[2]);

        test = test && (individual.add(2, testAccounts[4]));
        testSize += 1;
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[3]);
        test = test && individual.get(1).equals(testAccounts[0]);
        test = test && individual.get(2).equals(testAccounts[4]);
        test = test && individual.get(3).equals(testAccounts[1]);
        test = test && individual.get(4).equals(testAccounts[2]);

        test = test && (individual.add(Individual.SIZE_DEFAULT - 1, testAccounts[5]));
        testSize += 1;
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[3]);
        test = test && individual.get(1).equals(testAccounts[0]);
        test = test && individual.get(2).equals(testAccounts[4]);
        test = test && individual.get(3).equals(testAccounts[1]);
        test = test && individual.get(4).equals(testAccounts[2]);
        test = test && individual.get(5).equals(testAccounts[5]);

        String testNumber = testAccounts[5].getNumber();
        test = test && individual.get(testNumber).equals(testAccounts[5]);

        test = test && individual.hasAccount(testNumber);
        test = test && !individual.hasAccount("sadasdasdas");

        Account oldAccount = individual.get(0);
        test = test && individual.set(0, testAccounts[6]).equals(oldAccount);
        test = test && !individual.get(0).equals(oldAccount);
        test = test && individual.get(0).equals(testAccounts[6]);
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[6]);
        test = test && individual.get(1).equals(testAccounts[0]);
        test = test && individual.get(2).equals(testAccounts[4]);
        test = test && individual.get(3).equals(testAccounts[1]);
        test = test && individual.get(4).equals(testAccounts[2]);
        test = test && individual.get(5).equals(testAccounts[5]);

        oldAccount = individual.get(2);
        test = test && individual.remove(2).equals(oldAccount);
        testSize -= 1;
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[6]);
        test = test && individual.get(1).equals(testAccounts[0]);
        test = test && individual.get(2).equals(testAccounts[1]);
        test = test && individual.get(3).equals(testAccounts[2]);
        test = test && individual.get(4).equals(testAccounts[5]);
        test = test && individual.get(5) == null;

        testNumber = testAccounts[0].getNumber();
        oldAccount = individual.get(1);
        test = test && individual.remove(testNumber).equals(oldAccount);
        testSize -= 1;
        test = test && testSize == individual.size();
        test = test && individual.get(0).equals(testAccounts[6]);
        test = test && individual.get(1).equals(testAccounts[1]);
        test = test && individual.get(2).equals(testAccounts[2]);
        test = test && individual.get(3).equals(testAccounts[5]);
        test = test && individual.get(4) == null;

        Account[] sortedAccounts = individual.sortedAccountsByBalance();
        double testBalance = 0;
        for (Account it : sortedAccounts) {
            test = test & it.getBalance() > testBalance;
            testBalance = it.getBalance();
        }

        testBalance = 0;
        testBalance += testAccounts[6].getBalance();
        testBalance += testAccounts[1].getBalance();
        testBalance += testAccounts[2].getBalance();
        testBalance += testAccounts[5].getBalance();
        test = test && testBalance == individual.totalBalance();

        test = test && individual.getIndex(testAccounts[5].getNumber()) == 3;

        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if (test) {
            result.append("COMPLETED: First constructor individual test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: First constructor individual test.");
        }
    }

    private static void SecondTestIndividual() {
        long start = System.currentTimeMillis();
        Individual individual = new Individual("testName", 5);
        boolean test;
        int testSize = 0;
        test = testSize == individual.size();

        Account[] testAccounts = getAccounts();
        for (int i = 0; i < 7; i++) {
            individual.add(testAccounts[i]);
            testSize += 1;
            test = test && testSize == individual.size();
        }

        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if (test) {
            result.append("COMPLETED: Second constructor individual test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: Second constructor individual test.");
        }
    }

    private static void ThirdTestIndividual() {
        long start = System.currentTimeMillis();
        Account[] testAccounts = getAccounts();
        Individual individual = new Individual("testName", testAccounts);
        boolean test;
        int testSize = testAccounts.length;
        test = testSize == individual.size();
        test = test && !testAccounts.toString().equals(individual.getAccounts().toString());

        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if (test) {
            result.append("COMPLETED: Third constructor individual test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: Third constructor individual test.");
        }
    }

    private static void FirstTestAccountManager() {
        long start = System.currentTimeMillis();
        AccountManager accountManager = new AccountManager(4);
        boolean test;
        int testSize = 0;
        Individual[] testIndividuals = getIndividuals();

        test = testSize == accountManager.size();

        test = test && accountManager.add(testIndividuals[0]);
        testSize += 1;
        test = test && testSize == accountManager.size();
        test = test && accountManager.get(accountManager.size()-1).equals(testIndividuals[0]);

        test = test && accountManager.add(testIndividuals[1]);
        testSize += 1;
        test = test && accountManager.get(accountManager.size()-1).equals(testIndividuals[1]);
        test = test && accountManager.add(testIndividuals[2]);
        testSize += 1;
        test = test && accountManager.get(accountManager.size()-1).equals(testIndividuals[2]);
        test = test && testSize == accountManager.size();

        test = test && !(accountManager.add(-1, testIndividuals[3]));
        test = test && testSize == accountManager.size();
        test = test && !(accountManager.add(Individual.SIZE_DEFAULT + 1, testIndividuals[3]));
        test = test && testSize == accountManager.size();
        test = test && !(accountManager.add(Individual.SIZE_DEFAULT, testIndividuals[3]));
        test = test && testSize == accountManager.size();

        test = test && (accountManager.add(0, testIndividuals[3]));
        testSize += 1;
        test = test && testSize == accountManager.size();
        test = test && accountManager.get(0).equals(testIndividuals[3]);
        test = test && accountManager.get(1).equals(testIndividuals[0]);
        test = test && accountManager.get(2).equals(testIndividuals[1]);
        test = test && accountManager.get(3).equals(testIndividuals[2]);

        test = test && (accountManager.add(2, testIndividuals[4]));
        testSize += 1;
        test = test && testSize == accountManager.size();
        test = test && accountManager.get(0).equals(testIndividuals[3]);
        test = test && accountManager.get(1).equals(testIndividuals[0]);
        test = test && accountManager.get(2).equals(testIndividuals[4]);
        test = test && accountManager.get(3).equals(testIndividuals[1]);
        test = test && accountManager.get(4).equals(testIndividuals[2]);

        Client oldIndividual = accountManager.get(0);
        test = test && accountManager.set(0, testIndividuals[2]).equals(testIndividuals[3]);
        test = test && !accountManager.get(0).equals(oldIndividual);
        test = test && accountManager.get(0).equals(testIndividuals[2]);
        test = test && testSize == accountManager.size();

        oldIndividual = accountManager.get(2);
        test = test && accountManager.remove(2).equals(oldIndividual);
        testSize -= 1;
        test = test && testSize == accountManager.size();
        test = test && accountManager.get(0).equals(testIndividuals[2]);
        test = test && accountManager.get(1).equals(testIndividuals[0]);
        test = test && accountManager.get(2).equals(testIndividuals[1]);
        test = test && accountManager.get(3).equals(testIndividuals[2]);
        test = test && accountManager.get(4) == null;

        Client[] sortedIndividuals = accountManager.sortedByBalanceClients();
        double testBalance = 0;
        for (Client it : sortedIndividuals) {
            test = test & it.totalBalance() >= testBalance;
            testBalance = it.totalBalance();
        }

        String testNumber = testIndividuals[0].get(2).getNumber();
        test = test && accountManager.getAccount(testNumber).equals(testIndividuals[0].get(2));

        Account oldAccount = testIndividuals[0].get(2);
        test = test && accountManager.removeAccount(oldAccount.getNumber()).equals(oldAccount);

        oldAccount = testIndividuals[0].get(0);
        test = test && accountManager.setAccount(oldAccount.getNumber(), testIndividuals[0].get(2)).equals(oldAccount);
        test = test && accountManager.getAccount(testIndividuals[0].get(2).getNumber()).equals(testIndividuals[0].get(2));

        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if (test) {
            result.append("COMPLETED: First constructor accountManager test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: First constructor accountManager test.");
        }
    }

    private static void SecondTestAccountManager() {
        long start = System.currentTimeMillis();
        Individual[] testIndividuals = getIndividuals();
        AccountManager accountManager = new AccountManager(testIndividuals);
        boolean test;
        int testSize = testIndividuals.length;
        test = testSize == accountManager.size();
        test = test && !testIndividuals.toString().equals(accountManager.getClients().toString());

        long end = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        if (test) {
            result.append("COMPLETED: Second constructor accountManager test.");
            result.append((double) end - start);
            System.out.println(result.toString());
        } else {
            System.out.println("ERROR: Second constructor accountManager test.");
        }
    }

    private static Account[] getAccounts() {
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

    private static Individual[] getIndividuals() {
        Account[] accounts = getAccounts();
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
