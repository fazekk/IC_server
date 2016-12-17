package responses;

import models.Response;
import models.Vector3;

/**
 * Created by Kesze on 2016.11.11..
 */
public class MoveResponse extends Response {

    private int id;
    private Vector3 newPosition;
    private float rotation;
    private Long timeStamp;
    private float horizontal;
    private float vertical;
    private boolean lshift;
    private boolean space;
    private boolean lctrl;

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

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(float horizontal) {
        this.horizontal = horizontal;
    }

    public float getVertical() {
        return vertical;
    }

    public void setVertical(float vertical) {
        this.vertical = vertical;
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
