package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public interface Client extends Iterable<Account>,  Comparable<Client>, Collection<Account> {
    boolean add(Account account);
    boolean add(int index, Account account) throws DuplicateAccountNumberException;
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
    Account set(int index, Account account) throws DuplicateAccountNumberException;
    Account remove(int index);
    Account remove(String accountNumber);

    @Override
    default boolean remove(Object o) {
        if (o == null) throw new NullPointerException();
        if (!(o instanceof Account)) throw new ClassCastException();
        return remove((Account) o);
    }

    boolean remove(Account account);
    default double debtTotal() {
        double debBalance = 0;
        for (Credit it : getCreditAccounts()) {
            debBalance += ((CreditAccount) it).getBalance();
        }
        return debBalance;
    }
    int size();
    Account[] toArray();
    default List<Account> sortedAccountsByBalance() {
        Account[] accounts = toArray();
        Arrays.sort(accounts);
        return Arrays.asList(accounts);
    }
    default double totalBalance() {
        double totalBalance = 0;
        for (Account account : toArray()) {
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
    default Collection<Credit> getCreditAccounts() {
        Account[] credits = new Account[size()];
        Account[] accounts = toArray();
        int j = 0;
        for (int i = 0; i < size(); i++) {
            if (accounts[i] != null && accounts[i] instanceof Credit) {
                credits[j++] = accounts[i];
            }
        }
        Credit[] result = new Credit[j];
        for (int i = 0, k = 0; i < size(); i++) {
            if (credits[i] != null) {
                result[k++] = (Credit) credits[i];
            }
        }
        return Arrays.asList(result);
    }

    default boolean isValidNumber(String accountNumber) {
        return AbstractAccount.pattern.matcher(accountNumber).matches();
    }

    default boolean isDuplicateNumber(String accountNumber) {
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();
        return hasAccount(accountNumber);
    }

    @Override
    default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    default boolean contains(Object o) {
        if (o == null) throw new NullPointerException();
        return hasAccount(((Account) o).getNumber());
    }

    @Override
    default <T> T[] toArray(T[] a) {
        return (T[]) toArray();
    }

    @Override
    default boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        for (Object it : c) {
            result = result && contains(it);
        }
        return result;
    }

    @Override
    default boolean addAll(Collection<? extends Account> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        for (Account it : c) {
            result = result && add(it);
        }
        return result;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean result = true;
        for (Object it : c) {
            result = result && add((Account) it);
        }
        return result;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        return false;
    }
}
