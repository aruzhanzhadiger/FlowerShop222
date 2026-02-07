package entity;

public class Buyer {
    private String name;
    private Role role;
    private String password;
    private int purchases;


    public Buyer(String name, Role role) {
        this.name = name;
        this.role = role;
        this.purchases = 0;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    public int getPurchases() {
        return purchases;
    }

    public void increasePurchases() {
        purchases++;
    }
}