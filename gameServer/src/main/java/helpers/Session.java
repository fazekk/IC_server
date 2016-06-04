package helpers;

import models.Client;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zipfs on 2015. 12. 19..
 */
public class Session {


    private static int sessionLength = 10;
    private static LinkedBlockingDeque<Client> users;


    public static void Init(){
        users = new LinkedBlockingDeque<>();
    }

    public static String generateSession(){
        String session = "";
        for(int i = 0;i<sessionLength;i++){
            Random r = new Random();
            session+=r.nextInt(9);

        }
        for(Client user : users){
            if(user.getSession().equals(session)){
                session = generateSession();
            }
        }
        return  session;
    }

    public static Client getUserWithSession(String session){
        Client selectedUser = null;
        for(Client user : users){
            if(user.getSession().equals(session)){
                return user;
            }
        }
        return selectedUser;
    }

    /**
     * @param user add a user to session
     * @return
     */
    public static String addUser(Client user){
        for(Client sysUser : users){
            if(sysUser.getUser().getUserID() == user.getUser().getUserID()){
                sysUser.getClientThread().shutDown();
                user.setSession(sysUser.getSession());
                users.remove(sysUser);
                users.add(user);
                return user.getSession();
            }
        }
        user.setSession(generateSession());
        users.add(user);
        return user.getSession();
    }

    public static void setSessionLength(int length){
        sessionLength = length;
    }

    public static int getSessionLength() {
        return sessionLength;
    }

    public static LinkedBlockingDeque<Client> getUsers() {
        return users;
    }
}
