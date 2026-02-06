package service;

import entity.Buyer;
import entity.Flower;
import repositories.FlowerRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerService {
    private FlowerRepository repo;

    public FlowerService(FlowerRepository repo) {
        this.repo = repo;
    }

    public void createFlower(Flower flower) throws SQLException {
        if (flower.getPrice() <= 0) {
            System.out.println("Price must be more than 0");
            return;
        }
        repo.addFlower(flower);
    }

    public List<Flower> listFlowers() {
        try {
            return repo.getALLFlowers();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Flower getFlowerById(int id) throws SQLException {
        return repo.getFlowerById(id);
    }

    public double buyFlower(Buyer buyer, int flowerId, int quantity) throws SQLException {
        Flower flower = repo.getFlowerById(flowerId);

        if (flower == null) {
            System.out.println("Flower not found");
            return 0;
        }

        if (quantity > flower.getStock()) {
            System.out.println("Not enough flowers in stock");
            return 0;
        }

        double total = flower.getPrice() * quantity;
        buyer.increasePurchases();

        if (buyer.getPurchases() % 11 == 0) {
            total *= 0.9;
            System.out.println("You received a 10% discount for your 11th purchase!");
        }

        repo.updateStock(flowerId, flower.getStock() - quantity);
        return total;
    }

    public void deleteFlower(int id) throws SQLException {
        Flower flower = repo.getFlowerById(id);
        if (flower == null) {
            System.out.println("Flower with this ID does not exist.");
            return;
        }
        repo.deleteFlower(id);
    }
}