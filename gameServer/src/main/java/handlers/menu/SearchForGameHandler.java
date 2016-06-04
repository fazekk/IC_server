package handlers.menu;

import com.j256.ormlite.support.ConnectionSource;
import constants.DefaultMessages;
import constants.Properties;
import handlers.game.GameHandler;
import interfaces.MessageReciver;
import helpers.Games;
import server.ServerVariables;
import helpers.Session;
import logger.Log;
import models.Client;
import thread.ClientThread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zipfs on 2015. 12. 20..
 */
public class SearchForGameHandler extends MessageReciver {

    private ConnectionSource connectionSource;


    public SearchForGameHandler(ConnectionSource connectionSource, ClientThread thread) {
        super(connectionSource, thread);
        this.connectionSource = connectionSource;
    }

    //Message: 7; session; gameType

    @Override
    public void onRecive(String[] message) {
        super.onRecive(message);

        Client user = Session.getUserWithSession(message[1]);
        if(Integer.parseInt(message[2]) == DefaultMessages.SEARCH_CANCEL){
            user.setStatus(0);
            user.setSelectedTeams(new ArrayList<>());
            user.setSelectedGameType(0);
            try {
                send(DefaultMessages.SEARCH+";"+DefaultMessages.SEARCH_CANCEL__ACCEPTED);
            } catch (IOException e) {
                Log.write(e);
            }
        }else {
            if(user.getSelectedTeams().size()>= Integer.parseInt(ServerVariables.getValue(Properties.PROP_TEAM_MAX_SIZE))) {
                user.setSelectedGameType(Integer.parseInt(message[2]));
                user.setStatus(Client.STATUS_WAIT_FOR_GAME);

                try {
                    send(DefaultMessages.SEARCH + ";" + DefaultMessages.SEARCH_COMMAND_ACCEPTED + "");
                } catch (IOException e) {
                    Log.write(e);
                }
                List<Client> waitingPlayers = new ArrayList<>();
                int i = 0;
                for (Client Client : Session.getUsers()) {
                    if (Client.getStatus() == Client.STATUS_WAIT_FOR_GAME
                            && Client.getSelectedGameType() == user.getSelectedGameType()
                            && i < user.getSelectedGameType()) {
                        waitingPlayers.add(Client);
                        i++;
                    }
                }
                if (waitingPlayers.size() == user.getSelectedGameType()) {
                    GameHandler gameHandler = new GameHandler(connectionSource, waitingPlayers);
                    Games.addGameHandler(gameHandler);
                }
            }else{
                try {
                    send(DefaultMessages.SEARCH+";"+DefaultMessages.NEED_SELECTED_TEAMS);
                } catch (IOException e) {
                    Log.write(e);
                }
            }
        }
    }


    @Override
    public void send(String message) throws IOException {
        super.send(message);
    }


}
