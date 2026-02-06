package entity;
import java.util.List;

public class Order {
    private int id;
    private Buyer buyer;
    private List<OrderItem> items;
    private double total;

    public Order(int id, Buyer buyer, List<OrderItem> items, double total) {
        this.id = id;
        this.buyer = buyer;
        this.items = items;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}