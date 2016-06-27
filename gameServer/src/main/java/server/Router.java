package server;

import com.j256.ormlite.support.ConnectionSource;
import constants.Constants;
import constants.DefaultMessages;
import handlers.menu.LoginHandler;
import interfaces.Reciver;
import logger.Log;
import models.Client;
import models.request.Request;
import thread.ClientThread;
import thread.UDPServeThread;

/**
 * Created by zipfs on 2016. 06. 03..
 * Route the messages to the processor classes
 */
public class Router extends Reciver {

    private ConnectionSource connectionSource;

    public Router(ConnectionSource connectionSource) {
        super(connectionSource);
        this.connectionSource = connectionSource;
    }

    @Override
    public void onRecive(String message, ClientThread thread) {
        try {
            Request request = Request.fromJson(message);
            int type = request.getType();
            if (type == DefaultMessages.LOGIN_UNAME_PASS) {
                LoginHandler loginHandler = new LoginHandler(connectionSource);
                loginHandler.onRecive(message, thread);
            } else {
                Client client = new Client();
                client.setClientThread(thread);
                send(DefaultMessages.BAD_REQUEST + "", Constants.TCP, client);
            }
        }catch (Exception e){
            try {
                Client client = new Client();
                client.setClientThread(thread);
                send(DefaultMessages.BAD_REQUEST + "", Constants.TCP, client);
            }catch(Exception e1){
                Log.write(e);
            }
        }
    }

    @Override
    public void onRecive(String message, UDPServeThread thread) {

    }
}
