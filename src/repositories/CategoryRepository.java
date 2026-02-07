package repositories;

import entity.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final Connection conn;

    public CategoryRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Category> getAll() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT id, name FROM categories ORDER BY id";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
        }
        return list;
    }

    public Category getById(int id) throws SQLException {
        String sql = "SELECT id, name FROM categories WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Category(rs.getInt("id"), rs.getString("name"));
                }
            }
        }
        return null;
    }
}
