package helpers;

import handlers.game.GameHandler;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by zipfs on 2015. 12. 22..
 */
public class Games {

    private static LinkedBlockingQueue<GameHandler> gameHandlers;

    public static void addGameHandler(GameHandler gameHandler){
        gameHandlers.add(gameHandler);
    }

    public static void removeGameHandler(GameHandler gameHandler){
        gameHandlers.remove(gameHandler);
    }

    public static void init(){
        gameHandlers = new LinkedBlockingQueue<>();
    }
}
