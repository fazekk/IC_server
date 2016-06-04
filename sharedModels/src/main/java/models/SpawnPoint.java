package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zipfs on 2016. 01. 16..
 */
@DatabaseTable(tableName = "spawnPoint")
public class SpawnPoint {

    public static final String SPAWN_ID = "spawnID";
    public static final String SPAWN_X = "spawnX";
    public static final String SPAWN_Y = "spawnY";
    public static final String SPAWN_Z = "spawnZ";

    @DatabaseField(columnName = SPAWN_ID,  generatedId = true)
    private int spawnID;

    @DatabaseField(columnName = Map.MAP_ID)
    private int mapID;

    @DatabaseField(columnName = SPAWN_X)
    private int spawnX;

    @DatabaseField(columnName = SPAWN_Y)
    private int spawnY;

    @DatabaseField(columnName = SPAWN_Z)
    private int spawnZ;

    public int getSpawnID() {
        return spawnID;
    }

    public void setSpawnID(int spawnID) {
        this.spawnID = spawnID;
    }

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public int getSpawnZ() {
        return spawnZ;
    }

    public void setSpawnZ(int spawnZ) {
        this.spawnZ = spawnZ;
    }


    public String toMessage() {
        return spawnZ + ";" + spawnY + ";" + spawnX;
    }
}
