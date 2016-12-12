package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kesze on 2016.11.01..
 */
@DatabaseTable(tableName = "spawn_point")
public class SpawnPoint {

    public static final String ID = "id";
    public static final String MAP_ID = "mapID";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String Z = "z";

    @DatabaseField(generatedId = true, columnName = ID)
    private int id;
    @DatabaseField(columnName = MAP_ID)
    private int mapID;
    @DatabaseField(columnName = X)
    private float x;
    @DatabaseField(columnName = Y)
    private float y;
    @DatabaseField(columnName = Z)
    private float z;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
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