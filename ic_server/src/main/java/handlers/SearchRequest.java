package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.CommunicationConstants;
import constants.Constants;
import controllers.Game;
import controllers.GameManagger;
import helpers.Session;
import interfaces.RequestHandler;
import logger.Log;
import models.Client;
import responses.SearchResponse;
import java.util.List;

/**
 * Created by Kesze on 2016.08.27..
 */
public class SearchRequest extends RequestHandler {
    private boolean state;
    //private int index = 0;
    //private String session;

    //
    // Ez határozza meg hogy mennyi legyen az egy játékhoz szükséges playerek száma
    //
    public static final int minimalPlayer = 2;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session);
        if(state == true)
        {
            MatchQueue.addClient(client);
        }
        else{
            MatchQueue.removePlayer(client);
        }
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setSuccess(true);
        searchResponse.setType(CommunicationConstants.SEARCH_RESPONSE);
        if(MatchQueue.waitingPlayersNumber()>= minimalPlayer)
        {
            searchResponse.setMatchFind(true);
            searchResponse.setMessage("Megvagyunk");
            List<Client> currentUsers = MatchQueue.getPlayers(minimalPlayer);
            for(Client player : currentUsers)  //k?ld ?zenetet az ?sszes queueban l?v? clientnek
            {
                try {
                    send(new ObjectMapper().writeValueAsString(searchResponse), Constants.TCP, player);
                }
                catch (Exception e)
                {
                    Log.write(e);
                }
            }
            Log.write("Megvagyunk");
            Game game = new Game(); //p?ld?nyos?t?s
            int index = GameManagger.getIndex();
            index = GameManagger.addGame(game); //a p?ld?nyos?tott gamet ?tadjuk az addgame met?dusnak
            game.init(currentUsers, index, connectionSource); //az init met?dusnak ?tadjuk a current userst ?s az id-t jelent? indexe
            for(int i=0; i<currentUsers.size();i++) {
                MatchQueue.removePlayer(currentUsers.get(i)); //az eddigi list?b?l elt?vol?tjuk a playereket
            }
        }
        else
        {
            if(isState() == false)
            {
                searchResponse.setMessage("T?r?lt");
                try {
                    send(new ObjectMapper().writeValueAsString(searchResponse), Constants.TCP, client);
                }
                catch (Exception e)
                {
                    Log.write(e);
                }
            }
            else {
                searchResponse.setMatchFind(false);
                searchResponse.setMessage("Keresek");
                Log.write("Nem vagyunk meg");
                try {
                    send(new ObjectMapper().writeValueAsString(searchResponse), Constants.TCP, client);
                } catch (Exception e) {
                    Log.write(e);
                }
            }
        }


    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}