package models;

/**
 * Created by fazek on 2016.11.16..
 */
public class Vector3 {

    private float x,y, z;

    public Vector3() {
    }


    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
