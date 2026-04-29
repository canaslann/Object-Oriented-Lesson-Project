import java.util.List;

public class Main {

    public static void main(String[] args) {
        GameManager manager = new GameManager();

        manager.addGame(new Game("Elden Ring", "RPG", 1200.0, "FromSoftware"));
        manager.addGame(new Game("CS:GO", "FPS", 0.0, "Valve"));
        manager.addGame(new Game("Baldur's Gate 3", "RPG", 800.0, "Larian Studios"));

        System.out.println("\n>>> TÜM OYUNLAR LİSTESİ <<<");
        List<Game> allGames = manager.getAllGames();
        manager.displayGames(allGames);

        System.out.println("\n>>> RPG KATEGORİSİNDEKİ OYUNLAR <<<");
        List<Game> rpgGames = manager.filterByCategory("RPG");
        manager.displayGames(rpgGames);
    }
}
