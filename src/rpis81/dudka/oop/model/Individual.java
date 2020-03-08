package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Individual implements Client, Cloneable {

    public static final int SIZE_DEFAULT = 16;
    public static final int CREDIT_SCORES_DEFAULT = 0;

    private Account[] accounts;
    private String name;
    private int size;
    private int creditScores;

    public Individual(String name) {
        this(name, SIZE_DEFAULT);
    }

    public Individual(String name, int size) {
        this(name, new Account[size]);
    }

    public Individual(String name, Account[] accounts) {
        if (name == null || accounts == null) throw new NullPointerException();
        this.name = name;
        this.accounts = new Account[accounts.length];
        this.creditScores = CREDIT_SCORES_DEFAULT;
        toFill(accounts);
    }

    //Метод для заполнения списка счетов из источника
    private void toFill(Account[] sourceArray) {
        if (sourceArray == null) throw new NullPointerException();
        int i = 0;
        for (Account it : sourceArray) {
            if (it != null ) {
                if (!isDuplicateNumber(it.getNumber())) {
                    this.accounts[i++] = it;
                }
            }
        }
        size = i;
    }

    public boolean add(Account account) {
        if (account == null) throw new NullPointerException();
        if (isDuplicateNumber(account.getNumber())) try {
            throw new DuplicateAccountNumberException();
        } catch (DuplicateAccountNumberException e) {
            e.printStackTrace();
            return false;
        }

        checkQuantity();
        this.accounts[size++] = account;
        return true;
    }

    @Override
    public void clear() {
        this.accounts = new Account[SIZE_DEFAULT];
    }

    public boolean add(int index, Account account) throws DuplicateAccountNumberException {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (account == null) throw new NullPointerException();
        if (isDuplicateNumber(account.getNumber())) throw new DuplicateAccountNumberException();

        //ToDo тут переделай
        if (index < this.accounts.length){
            if (size + 1 > this.accounts.length) {
                increaseArray();
            }
            if (index < size) {
                int length = (size - index);
                for (int i = 0, j = size; i < length; i++) {
                    Account tmp = this.accounts[j];
                    this.accounts[j] = this.accounts[j-1];
                    this.accounts[j-1] = tmp;
                    j--;
                }
                this.accounts[index] = account;
                size += 1;
            } else {
                add(account);
            }
            return true;
        }
        return false;
    }

    public Account get(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index < size){
            return this.accounts[index];
        }
        throw new NoSuchElementException();
    }

    public Account set(int index, Account account) throws DuplicateAccountNumberException {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (account == null) throw new NullPointerException();
        if (isDuplicateNumber(account.getNumber())) throw new DuplicateAccountNumberException();

        if (index < size){
            Account oldAccount = this.accounts[index];
            this.accounts[index] = account;
            return oldAccount;
        }
        throw new NoSuchElementException();
    }

    public Account remove(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index < size){
            return toRemove(index);
        }
        throw new NoSuchElementException();
    }

    public Account remove(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();
            for (int i = 0; i < size; i++) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    return toRemove(i);
                }
            }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(Account account) {
        if (account == null) throw new NullPointerException();
        try {
            remove(account.getNumber());
            return true;
        }catch (NoSuchElementException ex) {
            return false;
        }
    }


    private Account toRemove(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Account oldAccount = this.accounts[index];
        this.accounts[index] = null;
        size--;
        shiftValues(index);
        return oldAccount;
    }

    public int size() {
        return size;
    }

    @Override
    public Account[] toArray() {
        Account[] newAccount = new Account[size];
        System.arraycopy(this.accounts, 0, newAccount, 0, size);
        return newAccount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null) throw new NullPointerException();
        this.name = name;
    }

    @Override
    public int getCreditScores() {
        return creditScores;
    }

    @Override
    public void addCreditScores(int creditScores) {
        this.creditScores += creditScores;
    }


    public int indexOf(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber))throw new InvalidAccountNumberException();
            for (int i = 0; i < size; i++) {
                if (this.accounts[i].getNumber().equals(accountNumber)) {
                    return i;
                }
            }
        throw new NoSuchElementException();
    }

    //Проверка, что если места нет)))
    private void checkQuantity(){
        if (size == this.accounts.length) {
            increaseArray();
        }
    }

    //Метод, увеличивающий объем
    private void increaseArray() {
        Account[] tmp = this.accounts;
        this.accounts = new Account[size * 2];
        toFill(tmp);
    }

    //Сдвиг всех элементов влево, с перемещением элемента по индексу в самый конец
    private void shiftValues(int index){
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        int length = this.accounts.length - 1;
        if (length - index >= 0) {
            System.arraycopy(this.accounts, index + 1, this.accounts, index, length - index);
        }
        this.accounts[length] = null;
    }

    //Для удобства))))
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client").append('\n');
        sb.append("name: ").append(name).append('\n');
        sb.append("creditScores: ").append(creditScores).append('\n');
        for (Account it : toArray()) {
            sb.append(it.toString()).append('\n');
        }
        sb.append("totalBalance").append(totalBalance());
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Individual)) return false;
        Individual that = (Individual) o;
        return size == that.size &&
                creditScores == that.creditScores &&
                Arrays.equals(accounts, that.accounts) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return  Arrays.hashCode(accounts) ^
                Objects.hash(name) ^ Objects.hash(size) ^
                Objects.hash(creditScores);
    }

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    @Override
    public int compareTo(Client o) {
        return (int) (this.totalBalance() - o.totalBalance());
    }

    private class AccountIterator implements Iterator<Account> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public Account next() {
            if (!hasNext()) throw new NoSuchElementException();
            return get(count++);
        }
    }
}
