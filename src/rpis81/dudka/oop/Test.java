package rpis81.dudka.oop;

import rpis81.dudka.oop.model.Account;
import rpis81.dudka.oop.model.AccountManager;
import rpis81.dudka.oop.model.Individual;

public class Test {
    public static void main(String[] args) {
        System.out.println("Я сделять!");
        lab1tests();
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

    private static void FirstTestAccount() {
        Account account = new Account();
        String oldNumberAccount = account.getNumber();
        double oldBalance = account.getBalance();
        String testNumberAccount = "142354534";
        double testBalance = 100000;
        account.setNumber(testNumberAccount);
        account.setBalance(testBalance);
        if ((account.getNumber().equals(testNumberAccount) &&
                account.getBalance() == testBalance ) &&
                (oldNumberAccount.equals(Account.NUMBER_DEFAULT) &&
                        oldBalance == Account.BALANCE_DEFAULT)) {
            System.out.println("COMPLETED: First constructor account test.");
        } else {
            System.out.println("ERROR: First constructor account test.");
        }
    }

    private static void SecondTestAccount() {
        Account account = new Account("63643278492", 213);
        String oldNumberAccount = account.getNumber();
        double oldBalance = account.getBalance();
        String testNumberAccount = "142354534";
        double testBalance = 100000;
        account.setNumber(testNumberAccount);
        account.setBalance(testBalance);
        if ((account.getNumber().equals(testNumberAccount) &&
                account.getBalance() == testBalance ) &&
                (!oldNumberAccount.equals(Account.NUMBER_DEFAULT) &&
                        oldBalance != Account.BALANCE_DEFAULT)) {
            System.out.println("COMPLETED: Second constructor account test.");
        } else {
            System.out.println("ERROR: Second constructor account test.");
        }
    }

    private static void FirstTestIndividual() {
        Individual individual = new Individual();
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

        if (test) {
            System.out.println("COMPLETED: First constructor individual test.");
        } else {
            System.out.println("ERROR: First constructor individual test.");
        }
    }

    private static void SecondTestIndividual() {
        Individual individual = new Individual(5);
        boolean test;
        int testSize = 0;
        test = testSize == individual.size();

        Account[] testAccounts = getAccounts();
        for (int i = 0; i < 7; i++) {
            individual.add(testAccounts[i]);
            testSize += 1;
            test = test && testSize == individual.size();
        }

        if (test) {
            System.out.println("COMPLETED: Second constructor individual test.");
        } else {
            System.out.println("ERROR: Second constructor individual test.");
        }
    }

    private static void ThirdTestIndividual() {
        Account[] testAccounts = getAccounts();
        Individual individual = new Individual(testAccounts);
        boolean test;
        int testSize = testAccounts.length;
        test = testSize == individual.size();
        test = test && !testAccounts.toString().equals(individual.getAccounts().toString());
        if (test) {
            System.out.println("COMPLETED: Third constructor individual test.");
        } else {
            System.out.println("ERROR: Third constructor individual test.");
        }
    }

    private static void FirstTestAccountManager() {
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

        Individual oldIndividual = accountManager.get(0);
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

        Individual[] sortedIndividuals = accountManager.sortedByBalanceIndividuals();
        double testBalance = 0;
        for (Individual it : sortedIndividuals) {
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

        if (test) {
            System.out.println("COMPLETED: First constructor accountManager test.");
        } else {
            System.out.println("ERROR: First constructor accountManager test.");
        }
    }

    private static void SecondTestAccountManager() {
        Individual[] testIndividuals = getIndividuals();
        AccountManager accountManager = new AccountManager(testIndividuals);
        boolean test;
        int testSize = testIndividuals.length;
        test = testSize == accountManager.size();
        test = test && !testIndividuals.toString().equals(accountManager.getIndividuals().toString());
        if (test) {
            System.out.println("COMPLETED: Second constructor accountManager test.");
        } else {
            System.out.println("ERROR: Second constructor accountManager test.");
        }
    }

    private static Account[] getAccounts() {
        Account[] testAccounts = new Account[25];
        testAccounts[0] = new Account("001", 34324);
        testAccounts[1] = new Account("002", 25435);
        testAccounts[2] = new Account("003", 85934);
        testAccounts[3] = new Account("004", 12324);
        testAccounts[4] = new Account("005", 56765);
        testAccounts[5] = new Account("006", 56856);
        testAccounts[6] = new Account("007", 34324);
        testAccounts[7] = new Account("008", 25435);
        testAccounts[8] = new Account("009", 85934);
        testAccounts[9] = new Account("010", 12324);
        testAccounts[10] = new Account("011", 56765);
        testAccounts[11] = new Account("012", 56856);
        testAccounts[12] = new Account("013", 34324);
        testAccounts[13] = new Account("014", 25435);
        testAccounts[14] = new Account("015", 85934);
        testAccounts[15] = new Account("016", 12324);
        testAccounts[16] = new Account("017", 56765);
        testAccounts[17] = new Account("018", 56856);
        testAccounts[18] = new Account("019", 34324);
        testAccounts[19] = new Account("020", 25435);
        testAccounts[20] = new Account("021", 85934);
        testAccounts[21] = new Account("022", 12324);
        testAccounts[22] = new Account("023", 56765);
        testAccounts[23] = new Account("024", 56856);
        testAccounts[24] = new Account("025", 56856);
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
                tmpIndividual = new Individual(tmpAccounts);
                testIndividuals[j++] = tmpIndividual;
                k = 0;
            } else {
                tmpAccounts[k++] = accounts[i];
            }
        }
        return testIndividuals;
    }
}
