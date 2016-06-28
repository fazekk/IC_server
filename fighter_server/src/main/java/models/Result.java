package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by zipfs on 2016. 01. 16..
 */
@DatabaseTable(tableName = "result")
public class Result {

    public static final String RESULT_ID = "resultID";
    public static final String RESULT_DATE = "result_date";
    public static final String PLAY_TIME = "play_time";
    public static final String SCORE = "score";
    public static final String GOLD_RECEIVE = "gold_receive";
    public static final String SUCCESS = "success";

    @DatabaseField(columnName = RESULT_ID, generatedId = true)
    private int resultID;

    @DatabaseField(columnName = RESULT_DATE)
    private Date date;

    @DatabaseField(columnName = SystemUser.USER_ID)
    private int userID;

    @DatabaseField(columnName = PLAY_TIME)
    private long playTime;

    @DatabaseField(columnName = SCORE)
    private int score;

    @DatabaseField(columnName = GOLD_RECEIVE)
    private int goldReceive;

    @DatabaseField(columnName = SUCCESS)
    private boolean succes;

    public int getResultID() {
        return resultID;
    }

    public void setResultID(int resultID) {
        this.resultID = resultID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGoldReceive() {
        return goldReceive;
    }

    public void setGoldReceive(int goldReceive) {
        this.goldReceive = goldReceive;
    }

    public boolean isSucces() {
        return succes;
    }

    public void setSucces(boolean succes) {
        this.succes = succes;
    }
}
