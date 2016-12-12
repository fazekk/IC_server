package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Kesze on 2016.08.27..
 */
@DatabaseTable(tableName = "user_statistics")
public class Statistics {
    public static final String USER_ID = "userID";
    public static final String KILL = "kill";
    public static final String DEATH ="death";

    @DatabaseField(columnName = USER_ID, id=true)
    private int userID;
    @DatabaseField(columnName = KILL)
    private int kill;
    @DatabaseField(columnName = DEATH)
    private int death;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }
}
