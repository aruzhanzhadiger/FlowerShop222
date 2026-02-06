package factory;


import entity.Buyer;
import entity.Role;

public class UserFactory {
    public static Buyer createBuyer(String name, Role role) {
        return new Buyer(name, role);
    }
}
