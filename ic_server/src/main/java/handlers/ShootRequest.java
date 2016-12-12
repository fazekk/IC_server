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
public class ShootRequest extends RequestHandler {

    private int newhp;
    private int dmg;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session); //sessionnel ell?tott cliensek
        Game game = GameManagger.getGame(client.getGameID()); //game lek?r?s
        game.shootRequest(client, newhp, dmg);
    }
}
