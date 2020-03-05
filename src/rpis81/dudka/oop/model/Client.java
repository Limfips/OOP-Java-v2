package rpis81.dudka.oop.model;

public interface Client {
    boolean add(Account account);
    boolean add(int index, Account account);
    Account get(int index);
    Account get(String accountNumber);
    boolean hasAccount(String accountNumber);
    Account set(int index, Account account);
    Account remove(int index);
    Account remove(String accountNumber);
    boolean remove(Account account);
    double debtTotal();
    int size();
    Account[] getAccounts();
    Account[] sortedAccountsByBalance();
    double totalBalance();
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
    Account[] getCreditAccounts();


}
