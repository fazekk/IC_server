package models;

/**
 * Created by Kesze on 2016.11.11..
 */
public class Enemy {

    private int id;
    private Vector3 positionEnemy;

    public Vector3 getPositionEnemy() {
        return positionEnemy;
    }

    public void setPositionEnemy(Vector3 positionEnemy) {
        this.positionEnemy = positionEnemy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
