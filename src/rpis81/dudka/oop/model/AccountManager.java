package rpis81.dudka.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AccountManager implements Iterable<Client> {

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
        if (sourceArray == null) throw new NullPointerException();
        int i = 0;
        for (Client it : sourceArray) {
            if (it != null) {
                this.clients[i++] = it;
            }
        }
        size = i;
    }

    public boolean add(Client client) {
        if (client == null) throw new NullPointerException();
        checkQuantity();
        this.clients[size++] = client;
        return true;
    }

    public boolean add(int index, Client client) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (client == null) throw new NullPointerException();

        if (index < this.clients.length){
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
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index < size){
            return this.clients[index];
        }
        throw new NoSuchElementException();
    }

    public Client set(int index, Client client) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (client == null) throw new NullPointerException();
        if (index < size){
            Client oldAccount = this.clients[index];
            this.clients[index] = client;
            return oldAccount;
        }
        throw new NoSuchElementException();
    }

    public Client remove(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index < size){
            Client oldAccount = this.clients[index];
            this.clients[index] = null;
            size--;
            shiftValues(index);
            return oldAccount;
        }
        throw new NoSuchElementException();
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
        Arrays.sort(clients);
        return clients;
    }

    public Account getAccount(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();

        for (int i = 0; i < size; i++) {
                if (this.clients[i].hasAccount(accountNumber)) {
                    return this.clients[i].get(accountNumber);
                }
            }
        throw new NoSuchElementException();
    }

    public int indexOf(Client client) {
        if (client == null) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (this.clients[i].equals(client)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public Account removeAccount(String accountNumber) {
        if (accountNumber == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();

        for (int i = 0; i < size; i++) {
                if (this.clients[i].hasAccount(accountNumber)) {
                    return this.clients[i].remove(accountNumber);
                }
            }
        throw new NoSuchElementException();
    }

    public boolean remove(Client client) {
        if (client == null) throw new NullPointerException();
        try {
            remove(indexOf(client));
            return true;
        }catch (NoSuchElementException ex) {
            return false;
        }
    }

    public Account setAccount(String accountNumber, Account account) throws DublicateAccountNumberException {
        if (accountNumber == null) throw new NullPointerException();
        if (account == null) throw new NullPointerException();
        if (!isValidNumber(accountNumber)) throw new InvalidAccountNumberException();

        for (int i = 0; i < size; i++) {
                if (this.clients[i].hasAccount(accountNumber)) {
                    return this.clients[i]
                            .set(this.clients[i].indexOf(accountNumber), account);
                }
            }
        throw new NoSuchElementException();
    }

    private boolean isValidNumber(String accountNumber) {
        return AbstractAccount.pattern.matcher(accountNumber).matches();
    }

    public Client[] getClientsWithOneCredit() {
        Client[] clients = new Client[size];
        int k = 0;
        for (Client it : this) {
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
        for (Client it: this) {
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
        final StringBuilder sb = new StringBuilder();
        for (Client it : getClients()) {
            sb.append(it).append('\n');
        }
        return sb.toString();
    }

    @Override
    public Iterator<Client> iterator() {
        return new ClientIterator();
    }

    private class ClientIterator implements Iterator<Client> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            return count != size;
        }

        @Override
        public Client next() {
            if (!hasNext()) throw new NoSuchElementException();
            return get(count++);
        }
    }
}
