package repositories;

import entity.Order;
import java.sql.SQLException;

public interface IOR {
    Order getFullOrderDescription(int orderId) throws SQLException;
}
