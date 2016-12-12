package responses;

import models.Response;
import models.Vector3;

/**
 * Created by Kesze on 2016.11.11..
 */
public class MoveResponse extends Response {

    private int id;
    private Vector3 newPosition;
    private Long timeStamp;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector3 getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Vector3 newPosition) {
        this.newPosition = newPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
