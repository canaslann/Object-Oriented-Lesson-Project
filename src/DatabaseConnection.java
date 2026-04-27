import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private final String URL = "jdbc:postgresql://localhost:5432/GameCart";
    private final String USER = "postgres";
    private final String PASSWORD = "123eeecan";

    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Veritabanı bağlantısı başarıyla kuruldu!");
        } catch (SQLException e) {
            System.out.println("Bağlantı hatası: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}