package handlers.menu;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.DefaultMessages;
import interfaces.MessageReciver;
import helpers.MD5Hash;
import helpers.Session;
import logger.Log;
import models.Client;
import models.SystemUser;
import thread.ClientThread;

import java.io.IOException;
import java.util.List;

/**
 * Created by zipfs on 2015. 12. 19..
 */
public class LoginHandler extends MessageReciver {

    private ConnectionSource connectionSource;
    private ClientThread clientThread;

    public LoginHandler(ConnectionSource connectionSource, ClientThread thread) {
        super(connectionSource, thread);
        this.connectionSource = connectionSource;
        this.clientThread = thread;
    }


    @Override
    public void onRecive(String[] message) {
        try {
            // instantiate the dao
            Dao<Client, String> userDao =
                    DaoManager.createDao(connectionSource, Client.class);

            // if you need to create the 'accounts' table make this call
            //TableUtils.createTable(connectionSource, Client.class);
            List<Client> users = userDao.queryBuilder().where().eq(SystemUser.USER_NAME, message[1])
                    .and().eq(SystemUser.USER_PASSWORD, MD5Hash.getMD5(message[2])).query();
            if (users.size()>0){
                Client user = users.get(0);
                user.setClientThread(clientThread);
                if (user != null) {
                    String session = Session.addUser(user);
                    send(DefaultMessages.LOGIN + ";" + session);
                }
            }else {
                send(DefaultMessages.LOGIN + ";" + DefaultMessages.LOGIN_FAILED);
            }
        }catch (Exception e){
            Log.write(e);
        }
    }

    @Override
    public void send(String message) throws IOException {
        super.send(message);
    }
}
