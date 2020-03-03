package rpis81.dudka.oop.model;

public class Entity implements Client {

    private Node head;
    private Node tail;
    private String name;
    private int size;

    public Entity(String name) {
        initHead();
        this.name = name;
    }

    public Entity(String name, Account[] accounts) {
        this(name);
        addAll(accounts);
    }

    private void initHead() {
        this.head = new Node(null, null);
        this.tail = this.head;
        this.head.next = this.head;
    }

    private boolean addAll(Account[] accounts) {
        boolean result = true;
        int numNew = accounts.length;
        if (numNew == 0) return true;
        for (Account it: accounts) {
            result = result && add(it);
        }
        return result;
    }

    @Override
    public boolean add(Account account) {
        return addLast(account);
    }

    @Override
    public boolean add(int index, Account account) {
        return addNodeByIndex(index, new Node(account, null));
    }

    @Override
    public Account get(int index) {
        if (checkIndex(index)) {
            return getNodeByIndex(index).value;
        }
        return null;
    }

    @Override
    public Account get(String accountNumber) {
        Account resultAccount = null;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (node.value.getNumber().equals(accountNumber)) {
                resultAccount = node.value;
                break;
            }
        }
        return resultAccount;
    }

    @Override
    public boolean hasAccount(String accountNumber) {
        return get(accountNumber) != null;
    }

    @Override
    public Account set(int index, Account account) {
        Account oldAccount = null;
        if (account != null) {
            oldAccount = setNodeByIndex(index, account);
        }
        return oldAccount;
    }

    @Override
    public Account remove(int index) {
        return removeNodeByIndex(index);
    }

    @Override
    public Account remove(String accountNumber) {
        return removeNodeByIndex(getIndex(accountNumber));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Account[] getAccounts() {
        Account[] accounts = new Account[this.size];
        int index = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            accounts[index++] = node.value;
        }
        return accounts;
    }

    @Override
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

    @Override
    public double totalBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public int getIndex(String accountNumber) {
        Account[] accounts = getAccounts();
        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // По Заданию
    private boolean addLast(Account account) {
        if (account != null) {
            Node newNode = new Node(account, this.head);
            this.tail.next = newNode;
            this.tail = newNode;
            size += 1;
            return true;
        }
        return false;
    }

    // По Заданию
    private Node getNodeByIndex(int index) {
        Node resultNode = null;
        int count = 0;
        for (Node node = this.head.next; node != this.head; node = node.next) {
            if (count++ == index) {
                resultNode = node;
                break;
            }
        }
        return resultNode;
    }

    // По Заданию
    private boolean addNodeByIndex(int index, Node addNode) {
        if (checkIndex(index)) {
            if (index == 0) {
                addNode.next = this.head.next;
                this.head.next = addNode;
                size += 1;
                return true;
            } else {
                int count = 0;
                for (Node node = this.head.next; node != this.head; node = node.next) {
                    if (count++ + 1 == index) {
                        addNode.next = node.next;
                        node.next = addNode;
                        size += 1;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // По Заданию
    private Account removeNodeByIndex(int index) {
        if (checkIndex(index)) {
            Node removeNode;
            if (index == 0) {
                removeNode = this.head.next;
                this.head.next = removeNode.next;
                size -= 1;
                return removeNode.value;
            } else {
                int count = 0;
                for (Node node = this.head.next; node != this.head; node = node.next) {
                    if (count++ + 1 == index) {
                        removeNode = node.next;
                        node.next = removeNode.next;
                        size -= 1;
                        return removeNode.value;
                    }
                }
            }
        }
        return null;
    }

    // По Заданию
    private Account setNodeByIndex(int index, Account account) {
        Account oldValue = null;
        Node node = getNodeByIndex(index);
        if (node != null) {
            oldValue = node.value;
            node.value = account;
        }
        return oldValue;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private boolean checkIndex(int index) {
        return (index > -1 && index < this.size);
    }

    public Account getFirst() {
        return head.next.value;
    }

    public Account getLast() {
        return tail.value;
    }

    private class Node {
        private Node next;
        private Account value;

        Node(Account value, Node next) {
            this.next = next;
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }
}
