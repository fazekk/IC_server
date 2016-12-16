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
    private String type;
    private boolean forward;
    private boolean left;
    private boolean right;
    private boolean backward;
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
