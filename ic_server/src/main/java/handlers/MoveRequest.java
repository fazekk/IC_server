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
    private float rotation;
    private boolean forward;
    private boolean left;
    private boolean right;
    private boolean backward;
    private boolean lshift;
    private boolean space;
    private boolean lctrl;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session); //sessionnel ell?tott cliensek
        Game game = GameManagger.getGame(client.getGameID()); //game lek?r?s
        game.moveRequest(client, newPosition, timeStamp, rotation,
                forward, left, right, backward, lshift, space, lctrl);
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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

    public boolean isLshift() {
        return lshift;
    }

    public void setLshift(boolean lshift) {
        this.lshift = lshift;
    }

    public boolean isSpace() {
        return space;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }

    public boolean isLctrl() {
        return lctrl;
    }

    public void setLctrl(boolean lctrl) {
        this.lctrl = lctrl;
    }
}
