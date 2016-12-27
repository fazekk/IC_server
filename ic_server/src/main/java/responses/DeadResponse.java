package responses;

import models.Response;

/**
 * Created by kesze on 2016.12.27..
 */
public class DeadResponse extends Response {

    private boolean dead;
    private int woundedID;

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getWoundedID() {
        return woundedID;
    }

    public void setWoundedID(int woundedID) {
        this.woundedID = woundedID;
    }
}
