package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

public interface Client extends Iterable<Account>,  Comparable<Client> {
    boolean add(Account account) throws DublicateAccountNumberException;
    boolean add(int index, Account account) throws DublicateAccountNumberException;
    Account get(int index);
    default Account get(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();
        for (Account account: this) {
            if (account != null) {
                if (account.getNumber().equals(accountNumber)){
                    return account;
                }
            }
        }
        throw new NoSuchElementException();
    }
    default boolean hasAccount(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        try {
            get(accountNumber);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    Account set(int index, Account account) throws DublicateAccountNumberException;
    Account remove(int index);
    Account remove(String accountNumber);
    boolean remove(Account account);
    default double debtTotal() {
        double debBalance = 0;
        for (Account it : getCreditAccounts()) {
            debBalance += it.getBalance();
        }
        return debBalance;
    }
    int size();
    Account[] getAccounts();
    default Account[] sortedAccountsByBalance() {
        Account[] accounts = getAccounts();
        Arrays.sort(accounts);
        return accounts;
    }
    default double totalBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }
    int indexOf(String accountNumber);
    String getName();
    void setName(String name);
    int getCreditScores();
    void addCreditScores(int creditScores);
    default ClientStatus getStatus() {
        int creditScores = getCreditScores();
        if (creditScores >= 5) {
            return ClientStatus.PLATINUM;
        } else if (creditScores >= 3) {
            return ClientStatus.GOLD;
        } else if (creditScores >= 0) {
            return ClientStatus.GOOD;
        } else if (creditScores >= -2) {
            return ClientStatus.RISKY;
        } else {
            return ClientStatus.BAD;
        }
    }
    default Account[] getCreditAccounts() {
        Account[] credits = new Account[size()];
        Account[] accounts = getAccounts();
        int j = 0;
        for (int i = 0; i < size(); i++) {
            if (accounts[i] != null && accounts[i] instanceof Credit) {
                credits[j++] = accounts[i];
            }
        }
        Account[] result = new Account[j];
        for (int i = 0, k = 0; i < size(); i++) {
            if (credits[i] != null) {
                result[k++] = credits[i];
            }
        }
        return result;
    }

    default boolean isValidNumber(String accountNumber) {
        return AbstractAccount.pattern.matcher(accountNumber).matches();
    }

    default boolean isDuplicateNumber(String accountNumber) {
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();
        return hasAccount(accountNumber);
    }
}
