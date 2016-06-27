package models.request;

import com.google.gson.Gson;

/**
 * Created by sinemissione on 2016.06.24..
 */
public class LoginRequest extends Request {

    private String userName;
    private String password;

    public static LoginRequest fromJson(String message){
        return new Gson().fromJson(message, LoginRequest.class);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
