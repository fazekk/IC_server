package models.request;

import com.google.gson.Gson;

/**
 * Created by sinemissione on 2016.06.24..
 */
public class Request {
    private int type;
    private String session;

    public static Request fromJson(String data){
        return new Gson().fromJson(data,Request.class);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
