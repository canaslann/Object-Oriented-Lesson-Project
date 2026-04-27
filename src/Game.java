import java.util.List;

public class Game {
    private String gameId;
    private String gameName;
    private String category;
    private double basePrice;
    private String producer;

    // 1. Constructor: Yeni oyun oluştururken kullanılır (Senin yazdığın, otomatik ID üreten)
    public Game(String gameName, String category, double basePrice, String producer) {
        this.gameId = generateGameID();
        this.gameName = gameName;
        this.category = category;
        this.basePrice = basePrice;
        this.producer = producer;
    }

    // 2. Constructor: Veritabanından veri ÇEKERKEN kullanılır (Mevcut ID'yi korur)
    public Game(String gameId, String gameName, String category, double basePrice, String producer) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.category = category;
        this.basePrice = basePrice;
        this.producer = producer;
    }

    public String getGameId() { return gameId; }
    public String getGameName() { return gameName; }
    public String getCategory() { return category; }
    public double getBasePrice() { return basePrice; }
    public String getProducer() { return producer; }

    private String generateGameID(){
        int randomNum = (int)(Math.random()*900000) + 100000; // 100000 - 999999
        return "GM-" + randomNum;
    }

    @Override
    public String toString() {
        return String.format("| %-12s | %-20s | %-10s | %-8.2f TL | %-15s |",
                gameId, gameName, category, basePrice, producer);
    }
}