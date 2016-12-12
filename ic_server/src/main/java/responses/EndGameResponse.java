package responses;

import models.Response;

/**
 * Created by kesze on 2016.12.03..
 */
public class EndGameResponse extends Response {

    private boolean victory;
    //private int time;
    private String type;

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    /*public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
