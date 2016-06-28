package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zipfs on 2016. 01. 16..
 */
@DatabaseTable(tableName = "map")
public class Map {

    public static final String MAP_ID = "mapID";
    public static final String MAP_NAME = "mapName";
    public static final String MAP_MAX_PLAYER = "mapMaxPlayer";


    @DatabaseField(columnName = MAP_ID, generatedId = true)
    private int mapID;

    @DatabaseField(columnName = MAP_NAME)
    private String mapName;

    @DatabaseField(columnName = MAP_MAX_PLAYER)
    private int mapMaxPlayer;

    public int getMapID() {
        return mapID;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getMapMaxPlayer() {
        return mapMaxPlayer;
    }

    public void setMapMaxPlayer(int mapMaxPlayer) {
        this.mapMaxPlayer = mapMaxPlayer;
    }
}
