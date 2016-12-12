package handlers;

import controllers.Game;
import controllers.GameManagger;
import helpers.Session;
import interfaces.RequestHandler;
import models.Client;
import models.Vector3;

/**
 * Created by Kesze on 2010.01.12..
 */
public class MoveRequest extends RequestHandler {

    private Long timeStamp;
    private Vector3 newPosition;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session); //sessionnel ell?tott cliensek
        Game game = GameManagger.getGame(client.getGameID()); //game lek?r?s
        game.moveRequest(client, newPosition, timeStamp);
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Vector3 getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Vector3 newPosition) {
        this.newPosition = newPosition;
    }
}
