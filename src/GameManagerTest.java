import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {

    // HER TESTTEN ÖNCE VERİTABANINI TEMİZLER
    @BeforeEach
    public void setUp() {
        String query = "DELETE FROM GAME";

        Connection conn = DatabaseConnection.getInstance().getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Temizleme hatası: " + e.getMessage());
        }
    }

    @Test
    public void testFilterByCategory() {
        GameManager manager = new GameManager();

        manager.addGame(new Game("GTA V", "Aksiyon", 500.0, "Rockstar Games"));
        manager.addGame(new Game("The Witcher 3", "RPG", 300.0, "CD Projekt Red"));
        manager.addGame(new Game("Cyberpunk 2077", "RPG", 400.0, "CD Projekt Red"));
        manager.addGame(new Game("FIFA 24", "Spor", 700.0, "EA Sports"));

        List<Game> rpgGames = manager.filterByCategory("RPG");

        //Doğrulama
        assertEquals(2, rpgGames.size(), "Filtrelenen listede 2 oyun olmalıydı!");
        assertEquals("The Witcher 3", rpgGames.get(0).getGameName(), "İlk RPG oyunu Witcher 3 olmalı!");
        assertEquals("CD Projekt Red", rpgGames.get(0).getProducer(), "Yapımcı adı doğru eşleşmeli!");
        assertTrue(rpgGames.get(0).getGameId().startsWith("GM-"), "ID'ler GM- formatıyla başlamalı!");
    }
}