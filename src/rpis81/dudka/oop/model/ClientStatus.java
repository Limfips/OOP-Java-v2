package rpis81.dudka.oop.model;

public enum ClientStatus {
    GOOD(0),
    GOLD(3),
    PLATINUM(5),
    RISKY(-2),
    BAD(-4);

    private int creditScoreBound;

    ClientStatus(int i) {
        this.creditScoreBound = i;
    }

    public int getCreditScoreBound() {
        return creditScoreBound;
    }
}
