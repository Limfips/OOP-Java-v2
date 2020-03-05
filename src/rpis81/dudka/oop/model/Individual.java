package rpis81.dudka.oop.model;

import java.util.Arrays;
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
        this.name = name;
        this.accounts = new Account[accounts.length];
        this.creditScores = CREDIT_SCORES_DEFAULT;
        toFill(accounts);
    }

    //Метод для заполнения списка счетов из источника
    private void toFill(Account[] sourceArray) {
        int i = 0;
        for (Account it : sourceArray) {
            if (it != null) {
                this.accounts[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Account account) {
        checkQuantity();
        this.accounts[size++] = account;
        return true;
    }

    public boolean add(int index, Account account) {
        if (index > -1 && index < this.accounts.length){
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
        if (index > -1 && index < size){
            return this.accounts[index];
        }
        return null;
    }

    public Account get(String accountNumber) {
        Account account;
        for (int i = 0; i < size; i++){
            account = this.accounts[i];
            if (account.getNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    public boolean hasAccount(String accountNumber) {
        Account account = get(accountNumber);
        return account != null;
    }

    public Account set(int index, Account account) {
        if (index > -1 && index < size){
            Account oldAccount = this.accounts[index];
            this.accounts[index] = account;
            return oldAccount;
        }
        return null;
    }

    public Account remove(int index) {
        if (index > -1 && index < size){
            return toRemove(index);
        }
        return null;
    }

    public Account remove(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber)) {
                return toRemove(i);
            }
        }
        return null;
    }

    @Override
    public boolean remove(Account account) {
        return remove(account.getNumber()) != null;
    }

    @Override
    public double debtTotal() {
        double debBalance = 0;
        for (Account it : getCreditAccounts()) {
            debBalance += it.getBalance();
        }
        return debBalance;
    }


    private Account toRemove(int index) {
        Account oldAccount = this.accounts[index];
        this.accounts[index] = null;
        size--;
        shiftValues(index);
        return oldAccount;
    }

    public int size() {
        return size;
    }

    public Account[] getAccounts() {
        Account[] newAccount = new Account[size];
        System.arraycopy(this.accounts, 0, newAccount, 0, size);
        return newAccount;
    }

    public Account[] sortedAccountsByBalance() {
        Account[] accounts = getAccounts();
        for (int i = size-1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if( accounts[j].getBalance() > accounts[j+1].getBalance() ){
                    Account tmp = accounts[j];
                    accounts[j] = accounts[j+1];
                    accounts[j+1] = tmp;
                }
            }
        }
        return accounts;
    }

    public double totalBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
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

    @Override
    public Account[] getCreditAccounts() {
        Account[] credits = new Account[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (this.accounts[i] != null && this.accounts[i] instanceof Credit) {
                credits[j++] = this.accounts[i];
            }
        }
        Account[] result = new Account[j];
        for (int i = 0, k = 0; i < size; i++) {
            if (credits[i] != null) {
                result[k++] = credits[i];
            }
        }
        return result;
    }

    public int indexOf(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (this.accounts[i].getNumber().equals(accountNumber)) {
                return i;
            }
        }
        return -1;
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
        for (Account it : getAccounts()) {
            sb.append(it.toString()).append('\n');
        }
        sb.append("totalBalance").append(totalBalance());
        sb.append('}');
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
}
