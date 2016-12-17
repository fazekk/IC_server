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

    private int dmg;
    private int woundedID;
    private Vector3 shootPosition;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session); //sessionnel ell?tott cliensek
        Game game = GameManagger.getGame(client.getGameID()); //game lek?r?s
        game.shootRequest(this, client);
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getWoundedID() {
        return woundedID;
    }

    public void setWoundedID(int woundedID) {
        this.woundedID = woundedID;
    }

    public Vector3 getShootPosition() {
        return shootPosition;
    }

    public void setShootPosition(Vector3 shootPosition) {
        this.shootPosition = shootPosition;
    }
}
