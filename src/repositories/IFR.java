package repositories;

import entity.Flower;
import java.sql.SQLException;
import java.util.List;

public interface IFR {
    void addFlower(Flower flower) throws SQLException;
    List<Flower> getAllFlowers() throws SQLException;
    Flower getFlowerById(int id) throws SQLException;
    void updateStock(int id, int stock) throws SQLException;
    void deleteFlower(int id) throws SQLException;
}
