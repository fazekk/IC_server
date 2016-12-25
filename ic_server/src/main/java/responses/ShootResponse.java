package responses;

import models.Response;

/**
 * Created by kesze on 2016.12.16..
 */
public class ShootResponse extends Response{

    private int newhp;
    private int woundedID;

    public int getNewhp() {
        return newhp;
    }

    public void setNewhp(int newhp) {
        this.newhp = newhp;
    }

    public int getWoundedID() {
        return woundedID;
    }

    public void setWoundedID(int woundedID) {
        this.woundedID = woundedID;
    }
}
