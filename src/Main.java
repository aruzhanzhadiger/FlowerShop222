import controllers.FlowerController;
import data.PostgresDB;
import data.interfaces.IDB;
import entity.Flower;
import repositories.FlowerRepository;
import service.FlowerService;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            IDB db = PostgresDB.getInstance(
                    "jdbc:postgresql://localhost:5432",
                    "postgres",
                    "0000",
                    "FlowerShop"
            );

            Connection conn = db.getConnection();

            FlowerRepository repo = new FlowerRepository(conn);
            FlowerService service = new FlowerService(repo);
            FlowerController controller = new FlowerController(service);

            controller.start();

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}