package rpis81.dudka.oop;

public class Individual {

    static final int SIZE_DEFAULT = 16;

    private Account[] accounts;
    private int size;

    public Individual() {
        this(SIZE_DEFAULT);
    }

    public Individual(int size) {
        this(new Account[size]);
    }

    public Individual(Account[] accounts) {
        this.accounts = new Account[accounts.length];
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

    public int getIndex(String accountNumber) {
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
        if (length - index >= 0) System.arraycopy(this.accounts, index + 1, this.accounts, index, length - index);
        this.accounts[length] = null;
    }

    //Для удобства))))
    public String toString() {
        final StringBuilder sb = new StringBuilder("Individual{");
        sb.append("accounts={");
        for (Account it : getAccounts()) {
            sb.append(it).append(", ");
        }
        sb.append("}");
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
