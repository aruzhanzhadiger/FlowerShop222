package repositories;


import entity.Buyer;
import entity.Flower;
import entity.Order;
import entity.OrderItem;
import entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOR {
    private Connection conn;

    public OrderRepository(Connection conn) {
        this.conn = conn;
    }

    public Order getFullOrderDescription(int orderId) throws SQLException {
        String sql = """
            SELECT o.id AS order_id,
                   b.id AS buyer_id, b.name AS buyer_name,
                   f.id AS flower_id, f.name, f.price,
                   oi.quantity
            FROM orders o
            JOIN buyers b ON o.buyer_id = b.id
            JOIN order_items oi ON o.id = oi.order_id
            JOIN flowers f ON oi.flower_id = f.id
            WHERE o.id = ?
        """;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();

        Buyer buyer = null;
        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        while (rs.next()) {
            if (buyer == null) {
                buyer = new Buyer(rs.getString("buyer_name"), Role.BUYER);
            }

            Flower flower = new Flower(
                    rs.getInt("flower_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    0
            );

            int qty = rs.getInt("quantity");
            total += flower.getPrice() * qty;

            items.add(new OrderItem(flower, qty));
        }

        return new Order(orderId, buyer, items, total);
    }
}