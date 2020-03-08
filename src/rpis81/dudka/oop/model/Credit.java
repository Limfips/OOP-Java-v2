package rpis81.dudka.oop.model;

import java.time.LocalDate;

public interface Credit {
    double getAPR();
    void setAPR(double APR);
    double nextPaymentValue();
    LocalDate nextPaymentDay();
}
