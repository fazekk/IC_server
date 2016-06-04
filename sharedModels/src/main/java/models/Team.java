package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by zipfs on 2015. 12. 24..
 */

@DatabaseTable(tableName = "team")
public class Team {

    public static final String TEAM_ID = "teamID";
    public static final String TEAM_SIZE = "teamSize";
    public static final String TEAM_VIEW_DISTANCE = "teamViewDistance";
    public static final String TEAM_PRICE = "teamPrice";
    public static final String TEAM_NAME = "teamName";
    public static final String TEAM_DESCRIPTION = "teamDescription";

    @DatabaseField(columnName = TEAM_ID, generatedId = true)
    private int teamID;

    @DatabaseField(columnName = TEAM_SIZE)
    private int teamSize;

    @DatabaseField(columnName = TEAM_VIEW_DISTANCE)
    private int viewDistance;

    @DatabaseField(columnName = Character.CHARACTER_ID)
    private int characterID;

    @DatabaseField(columnName = TEAM_PRICE)
    private int price;

    @DatabaseField(columnName = TEAM_NAME)
    private String teamName;

    @DatabaseField(columnName = TEAM_DESCRIPTION)
    private String teamDescription;

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public String toMessage() {
        return teamID +
                ";" + teamSize +
                ";" + viewDistance +
                ";" + price +
                ";" + teamName +
                ";" + teamDescription;
    }
}
