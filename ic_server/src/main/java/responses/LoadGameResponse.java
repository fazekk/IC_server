package responses;

import models.Response;

/**
 * Created by fazek on 2016.11.16..
 */

public class LoadGameResponse extends Response {

    private int mapID;
    private String type;

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
