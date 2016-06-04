package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zipfs on 2015. 12. 30..
 */
@DatabaseTable(tableName = "user_has_team")
public class UserHasTeam {

    public static final String USER_TEAM_ID = "userTeamID";
    public static final String TEAM_LEVEL = "teamLevel";

    @DatabaseField(columnName = USER_TEAM_ID, generatedId = true)
    private int userTeamID;

    @DatabaseField(columnName = Team.TEAM_ID)
    private int teamID;

    @DatabaseField(columnName = SystemUser.USER_ID)
    private int userID;

    @DatabaseField(columnName = TEAM_LEVEL)
    private int level;

    public int getUserTeamID() {
        return userTeamID;
    }

    public void setUserTeamID(int userTeamID) {
        this.userTeamID = userTeamID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
