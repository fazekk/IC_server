package handlers;

import interfaces.RequestHandler;

/**
 * Created by zipfs on 2015. 12. 29..
 */
public class UserStatService extends RequestHandler {

    @Override
    public void onRecive(String message) {
        super.onRecive(message);


        /*
        Client user = Session.getUserWithSession(message[1]);
        try {
            if(Integer.parseInt(message[2]) == DefaultMessages.USER_STAT_USER) {
                send(DefaultMessages.USER_STAT_REQUEST+";"+DefaultMessages.USER_STAT_USER
                        + ";" + user.toMessage());
            }else if(Integer.parseInt(message[2]) == DefaultMessages.USER_STAT_SERVER_VAR){
                send(DefaultMessages.USER_STAT_REQUEST + ";" + DefaultMessages.USER_STAT_SERVER_VAR
                        + ";" + ServerVariables.getValue(Properties.PROP_TEAM_MAX_SIZE));
            }
        }catch(Exception e){
            Log.write(e);
        }*/
    }
/*
    @Override
    public void send(String message) throws IOException {
        super.send(message);
    }*/
}
