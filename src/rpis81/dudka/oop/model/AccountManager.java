package rpis81.dudka.oop.model;

public class AccountManager {

    private Client[] clients;
    private int size;

    public AccountManager(int size) {
        this(new Client[size]);
    }

    public AccountManager(Client[] clients) {
        this.clients = new Client[clients.length];
        toFill(clients);
    }

    private void toFill(Client[] sourceArray) {
        int i = 0;
        for (Client it : sourceArray) {
            if (it != null) {
                this.clients[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Client client) {
        checkQuantity();
        this.clients[size++] = client;
        return true;
    }

    public boolean add(int index, Client client) {
        if (index > -1 && index < this.clients.length){
            if (size + 1 > this.clients.length) {
                increaseArray();
            }
            if (index < size) {
                int length = (size - index);
                for (int i = 0, j = size; i < length; i++) {
                    Client tmp = this.clients[j];
                    this.clients[j] = this.clients[j-1];
                    this.clients[j-1] = tmp;
                    j--;
                }
                this.clients[index] = client;
                size += 1;
            } else {
                add(client);
            }
            return true;
        }
        return false;
    }

    public Client get(int index) {
        if (index > -1 && index < size){
            return this.clients[index];
        }
        return null;
    }

    public Client set(int index, Client client) {
        if (index > -1 && index < size){
            Client oldAccount = this.clients[index];
            this.clients[index] = client;
            return oldAccount;
        }
        return null;
    }

    public Client remove(int index) {
        if (index > -1 && index < size){
            Client oldAccount = this.clients[index];
            this.clients[index] = null;
            size--;
            shiftValues(index);
            return oldAccount;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public Client[] getClients() {
        Client[] newClient = new Client[size];
        System.arraycopy(this.clients, 0, newClient, 0, size);
        return newClient;
    }

    public Client[] sortedByBalanceClients() {
        Client[] clients = getClients();
        for (int i = size-1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if( clients[j].totalBalance() > clients[j+1].totalBalance() ){
                    Client tmp = clients[j];
                    clients[j] = clients[j+1];
                    clients[j+1] = tmp;
                }
            }
        }
        return clients;
    }

    public Account getAccount(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (this.clients[i].hasAccount(accountNumber)) {
                return this.clients[i].get(accountNumber);
            }
        }
        return null;
    }

    public Account removeAccount(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (this.clients[i].hasAccount(accountNumber)) {
                return this.clients[i].remove(accountNumber);
            }
        }
        return null;
    }

    public Account setAccount(String accountNumber, Account account) {
        for (int i = 0; i < size; i++) {
            if (this.clients[i].hasAccount(accountNumber)) {
                return this.clients[i]
                        .set(this.clients[i].getIndex(accountNumber), account);
            }
        }
        return null;
    }

    public Client[] getClientsWithOneCredit() {
        Client[] clients = new Client[size];
        int k = 0;
        for (Client it : getClients()) {
            if (it.getCreditAccounts().length > 0) {
                clients[k++] = it;
            }
        }
        Client[] result = new Client[k];
        System.arraycopy(clients, 0, result, 0, k);
        return result;
    }

    public Client[] getBedClientsWithOneCredit() {
        Client[] clients = new Client[size];
        int k = 0;
        for (Client it : getClients()) {
            if (it.getCreditAccounts().length > 0 && it.getStatus().equals(ClientStatus.BAD)) {
                clients[k++] = it;
            }
        }
        Client[] result = new Client[k];
        System.arraycopy(clients, 0, result, 0, k);
        return result;
    }

    private void checkQuantity(){
        if (size == this.clients.length) {
            increaseArray();
        }
    }

    private void increaseArray() {
        Client[] tmp = this.clients;
        this.clients = new Client[size * 2];
        toFill(tmp);
    }

    private void shiftValues(int index){
        int length = this.clients.length - 1;
        if (length - index >= 0) {
            System.arraycopy(this.clients, index + 1, this.clients, index, length - index);
        }
        this.clients[length] = null;
    }


    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountManager{");
        sb.append("clients={");
        for (Client it : getClients()) {
            sb.append(it).append(", ");
        }
        sb.append("}");
        sb.append(", size=").append(size);
        sb.append('}');
        return sb.toString();
    }
}
