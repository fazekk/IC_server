package constants;

/**
 * Created by zipfs on 2015. 12. 19..
 */
public class DefaultMessages {

    public static final int PING_REQUEST = 44;
    public static final int PING_RESPONSE = 45;

    //Login
    public static final int LOGIN = 1;
    public static final int LOGIN_UNAME_PASS = 2;
    public static final int LOGIN_FAILED = 3;
    public static final int BAD_REQUEST = 4;

    //Opponent search
    public static final int SEARCH = 6;
    public static final int SEARCH_CANCEL  = 7;
    public static final int GAME_FOUND = 8;
    public static final int SEARCH_COMMAND_ACCEPTED = 9;
    public static final int SEARCH_CANCEL__ACCEPTED = 30;

    //Shop messages
    public static final int SHOP_MESSAGE = 10;
    public static final int SHOP_BUY_TEAM = 11;
    public static final int SHOP_CANT_BUY_THIS = 12;
    public static final int SHOP_BUY_SUCCESSFUL = 13;
    public static final int SHOP_GET_ITEMS = 14;
    public static final int SHOP_TEAM = 15;

    //Team select
    public static final int TEAM_SELECT = 16;
    public static final int SELECT_SUCCESSFUL = 17;
    public static final int SELECT_FAILED = 18;
    public static final int GET_AVAILABLE_TEAMS = 31;
    public static final int SELECT_TEAM = 32;
    public static final int TEAM_LIST = 33;

    //Game initialization
    public static final int GAME_MESSAGE = 19;
    public static final int READY_FOR_GAME = 20;
    public static final int WAITING_FOR_OTHER_PLAYERS = 24;
    public static final int GAME_READY = 25;
    public static final int SPAWN_TEAM = 48;
    public static final int ENEMY_TEAM_VISIBLE = 49;

    //Game messages
    public static final int NEED_SELECTED_TEAMS = 34;

    //Request user stats
    public static final int USER_STAT_REQUEST = 27;
    public static final int USER_STAT_USER = 46;
    public static final int USER_STAT_SERVER_VAR = 47;
}
