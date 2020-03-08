package rpis81.dudka.oop.model;

import java.time.LocalDate;

public interface Account extends Comparable<Account> {
    String getNumber();
    void setNumber(String number);
    double getBalance();
    void setBalance(double balance);
    LocalDate getCreationDate();
    LocalDate getExpirationDate();
    void setExpirationDate(LocalDate date);
    int monthsQuantityBeforeExpiration();
}
