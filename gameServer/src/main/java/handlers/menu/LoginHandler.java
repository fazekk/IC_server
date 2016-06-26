package handlers.menu;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.Constants;
import constants.DefaultMessages;
import interfaces.MessageHandler;
import helpers.MD5Hash;
import helpers.Session;
import logger.Log;
import models.Client;
import models.request.LoginRequest;
import models.request.Request;
import models.SystemUser;
import thread.ClientThread;

import java.io.IOException;
import java.util.List;

/**
 * Created by zipfs on 2015. 12. 19..
 */
public class LoginHandler extends MessageHandler {

    private ConnectionSource connectionSource;

    public LoginHandler(ConnectionSource connectionSource) {
        super(connectionSource);
        this.connectionSource = connectionSource;
    }

    public void onRecive(String message, ClientThread thread) {
        try {
            // instantiate the dao
            LoginRequest request = LoginRequest.fromJson(message);

            Dao<SystemUser, String> userDao =
                    DaoManager.createDao(connectionSource, SystemUser.class);

            List<SystemUser> users = userDao.queryBuilder().where().eq(SystemUser.USER_NAME, request.getUserName())
                    .and().eq(SystemUser.USER_PASSWORD, MD5Hash.getMD5(request.getPassword())).query();
            if (users.size()>0){
                Client user = new Client(users.get(0));
                user.setClientThread(thread);
                if (user != null) {
                    String session = Session.addUser(user);
                    send(DefaultMessages.LOGIN + ";" + session, Constants.TCP, user);
                }
            }else {
                Client client = new Client();
                client.setClientThread(thread);
                send(DefaultMessages.LOGIN + ";" + DefaultMessages.LOGIN_FAILED, Constants.TCP, client);
            }
        }catch (Exception e){
            Log.write(e);
        }
    }
}
