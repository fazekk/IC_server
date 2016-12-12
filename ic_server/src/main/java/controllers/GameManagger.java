package controllers;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kesze on 2010.01.12..
 */
public class GameManagger {

    private static int index;

    // Ez a tömb KEY és VALUE alapján tárolja a létrehozott játékokat
    private static Map<Integer, Game> games = new HashMap<Integer, Game>();

    public static int addGame(Game game){
        // A tömbünkbe helyezzük az épp aktuális game-et
        games.put(index, game);
        // Helyezés után előkészítjük a következő game-nek az ID-t
        index++;
        // Visszatérési érték-1 a megfelelő érték érdekében
        return index-1;
    }

    // Eltávolítjuk a játékot a végeztével
    public static void gameEnded(int id){
            games.remove(id);
    }

    // Lekérjük az ID alapján az adott játékot
    public static Game getGame(int id){
        return games.get(id);
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        GameManagger.index = index;
    }
}
