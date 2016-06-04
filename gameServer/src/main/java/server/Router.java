package server;

import com.j256.ormlite.support.ConnectionSource;
import constants.DefaultMessages;
import handlers.UserStatService;
import handlers.game.GameHandler;
import handlers.menu.LoginHandler;
import handlers.menu.SearchForGameHandler;
import handlers.menu.ShopHandler;
import handlers.menu.TeamSelectHandler;
import helpers.Session;
import interfaces.MessageReciver;
import logger.Log;
import models.Client;
import thread.ClientThread;

/**
 * Created by zipfs on 2016. 06. 03..
 * Route the messages to the processor classes
 */
public class Router extends MessageReciver {

    private ConnectionSource connectionSource;
    private ClientThread clientThread;
    private GameHandler gameHandler;

    public Router(ConnectionSource connectionSource, ClientThread thread) {
        super(connectionSource, thread);
        this.connectionSource = connectionSource;
        this.clientThread = thread;
    }

    @Override
    public void onRecive(String[] message) {
        try {
            if (message.length > 2) {
                if (message[1].length() == Session.getSessionLength()) {
                    Client user = Session.getUserWithSession(message[1]);
                    if (!user.getClientThread().equals(clientThread)) {
                        user.getClientThread().shutDown();
                        user.setClientThread(clientThread);
                    }
                }
            }

            int type = Integer.parseInt(message[0]);

            if (type == DefaultMessages.LOGIN_UNAME_PASS) {
                LoginHandler loginHandler = new LoginHandler(connectionSource, clientThread);
                loginHandler.onRecive(message);
            } else if (type == DefaultMessages.SEARCH) {
                SearchForGameHandler searchForGameHandler = new SearchForGameHandler(connectionSource, clientThread);
                searchForGameHandler.onRecive(message);
            } else if (gameHandler != null) {
                if (type == DefaultMessages.GAME_MESSAGE) {
                    gameHandler.recive(message);
                }
            } else if (type == DefaultMessages.SHOP_MESSAGE) {
                ShopHandler shopHandler = new ShopHandler(connectionSource, clientThread);
                shopHandler.onRecive(message);
            } else if (type == DefaultMessages.TEAM_SELECT) {
                TeamSelectHandler teamSelectHandler = new TeamSelectHandler(connectionSource, clientThread);
                teamSelectHandler.onRecive(message);
            } else if (type == DefaultMessages.USER_STAT_REQUEST) {
                UserStatService userStatService = new UserStatService(connectionSource, clientThread);
                userStatService.onRecive(message);
            } else {
                send(DefaultMessages.BAD_REQUEST + "");
            }
        }catch (Exception e){
            Log.write(e);
        }
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
