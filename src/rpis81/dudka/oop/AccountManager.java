package rpis81.dudka.oop;

public class AccountManager {

    private Individual[] individuals;
    private int size;

    public AccountManager(int size) {
        this(new Individual[size]);
    }

    public AccountManager(Individual[] individuals) {
        this.individuals = new Individual[individuals.length];
        toFill(individuals);
    }

    private void toFill(Individual[] sourceArray) {
        int i = 0;
        for (Individual it : sourceArray) {
            if (it != null) {
                this.individuals[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Individual individual) {
        checkQuantity();
        this.individuals[size++] = individual;
        return true;
    }

    public boolean add(int index, Individual individual) {
        if (index > -1 && index < this.individuals.length){
            if (size + 1 > this.individuals.length) {
                increaseArray();
            }
            if (index < size) {
                int length = (size - index);
                for (int i = 0, j = size; i < length; i++) {
                    Individual tmp = this.individuals[j];
                    this.individuals[j] = this.individuals[j-1];
                    this.individuals[j-1] = tmp;
                    j--;
                }
                this.individuals[index] = individual;
                size += 1;
            } else {
                add(individual);
            }
            return true;
        }
        return false;
    }

    public Individual get(int index) {
        if (index > -1 && index < size){
            return this.individuals[index];
        }
        return null;
    }

    public Individual set(int index, Individual individual) {
        if (index > -1 && index < size){
            Individual oldAccount = this.individuals[index];
            this.individuals[index] = individual;
            return oldAccount;
        }
        return null;
    }

    public Individual remove(int index) {
        if (index > -1 && index < size){
            Individual oldAccount = this.individuals[index];
            this.individuals[index] = null;
            size--;
            shiftValues(index);
            return oldAccount;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public Individual[] getIndividuals() {
        Individual[] newIndividual = new Individual[size];
        System.arraycopy(this.individuals, 0, newIndividual, 0, size);
        return newIndividual;
    }

    public Individual[] sortedByBalanceIndividuals() {
        Individual[] individuals = getIndividuals();
        for (int i = size-1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if( individuals[j].totalBalance() > individuals[j+1].totalBalance() ){
                    Individual tmp = individuals[j];
                    individuals[j] = individuals[j+1];
                    individuals[j+1] = tmp;
                }
            }
        }
        return individuals;
    }

    public Account getAccount(String accountNumber) {
        for (int i = 0; i < size; i++) {

            if (this.individuals[i].hasAccount(accountNumber)) {
                return this.individuals[i].get(accountNumber);
            }
        }
        return null;
    }

    public Account removeAccount(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (this.individuals[i].hasAccount(accountNumber)) {
                return this.individuals[i].remove(accountNumber);
            }
        }
        return null;
    }

    public Account setAccount(String accountNumber, Account account) {
        for (int i = 0; i < size; i++) {
            if (this.individuals[i].hasAccount(accountNumber)) {
                return this.individuals[i]
                        .set(this.individuals[i].getIndex(accountNumber), account);
            }
        }
        return null;
    }

    private void checkQuantity(){
        if (size == this.individuals.length) {
            increaseArray();
        }
    }

    private void increaseArray() {
        Individual[] tmp = this.individuals;
        this.individuals = new Individual[size * 2];
        toFill(tmp);
    }

    private void shiftValues(int index){
        int length = this.individuals.length - 1;
        if (length - index >= 0) System.arraycopy(this.individuals, index + 1, this.individuals, index, length - index);
        this.individuals[length] = null;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountManager{");
        sb.append("individuals={");
        for (Individual it : getIndividuals()) {
            sb.append(it).append(", ");
        }
        sb.append("}");
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
