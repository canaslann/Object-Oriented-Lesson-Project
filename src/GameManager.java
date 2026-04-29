import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GameManager {

    // 1. ÖZELLİK: Veritabanına Oyun Ekleme
    public void addGame(Game game) {
        String query = "INSERT INTO GAME (game_id, game_name, category, base_price, producer) VALUES (?, ?, ?, ?, ?)";

        // BAĞLANTIYI TRY DIŞINA ALDIK (Kapanmasın diye)
        Connection conn = DatabaseConnection.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, game.getGameId());
            pstmt.setString(2, game.getGameName());
            pstmt.setString(3, game.getCategory());
            pstmt.setDouble(4, game.getBasePrice());
            pstmt.setString(5, game.getProducer());

            pstmt.executeUpdate();
            System.out.println(game.getGameName() + " veritabanına başarıyla eklendi.");
        } catch (Exception e) {
            System.out.println("Ekleme hatası: " + e.getMessage());
        }
    }

    // 2. ÖZELLİK: Tüm Oyunları Listeleme
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM GAME";

        Connection conn = DatabaseConnection.getInstance().getConnection();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Game game = new Game(
                        rs.getString("game_id"),
                        rs.getString("game_name"),
                        rs.getString("category"),
                        rs.getDouble("base_price"),
                        rs.getString("producer")
                );
                games.add(game);
            }
        } catch (Exception e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }
        return games;
    }

    // 3. ÖZELLİK: Kategoriye Göre Filtreleme
    public List<Game> filterByCategory(String category) {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM GAME WHERE category = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Game game = new Game(
                            rs.getString("game_id"),
                            rs.getString("game_name"),
                            rs.getString("category"),
                            rs.getDouble("base_price"),
                            rs.getString("producer")
                    );
                    games.add(game);
                }
            }
        } catch (Exception e) {
            System.out.println("Filtreleme hatası: " + e.getMessage());
        }
        return games;
    }

    public void displayGames(List<Game> games) {
        if (games.isEmpty()) {
            System.out.println("Görüntülenecek oyun bulunamadı.");
            return;
        }
        for (Game g : games) {
            System.out.println(g);
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
}