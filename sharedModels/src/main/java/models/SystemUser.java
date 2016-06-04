package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zipfs on 2015. 12. 19..
 */
@DatabaseTable(tableName = "system__user")
public class SystemUser {

    public static final String USER_ID = "userID";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_MONEY = "money";

    @DatabaseField(columnName = USER_ID, generatedId = true)
    private int userID;

    @DatabaseField(columnName = USER_EMAIL)
    private String email;

    @DatabaseField(columnName = USER_NAME)
    private String name;

    @DatabaseField(columnName = USER_PASSWORD)
    private String password;

    @DatabaseField(columnName = USER_MONEY)
    private int money;

    public String toMessage() {
        return email + ";" + name + ";" + money + ";" + userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
