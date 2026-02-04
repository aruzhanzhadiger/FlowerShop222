package entity;

public class Buyer {
    private String name;
    private int purchases;

    public Buyer(String name) {
        this.name = name;
        this.purchases = 0;
    }

    public String getName() {
        return name;
    }

    public int getPurchases() {
        return purchases;
    }

    public void increasePurchases() {
        purchases++;
    }
}
