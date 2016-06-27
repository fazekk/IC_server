package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.support.ConnectionSource;
import constants.Constants;
import handlers.LoginRequest;
import interfaces.RequestHandler;
import interfaces.Reciver;
import logger.Log;
import models.Client;
import thread.ClientThread;
import thread.UDPServeThread;

/**
 * Created by zipfs on 2016. 06. 03..
 * Route the messages to the processor classes
 */
public class Router extends Reciver {

    public Router(ConnectionSource connectionSource) {
        super(connectionSource);
    }

    @Override
    public void onRecive(String message, ClientThread thread) {


        try {
            RequestHandler requestHandler = new ObjectMapper().readValue(message, RequestHandler.class);
            requestHandler.setConnectionSource(connectionSource);
            requestHandler.setThread(thread);
            requestHandler.onRecive(message);
            /*
            if (type == DefaultMessages.LOGIN_UNAME_PASS) {
                LoginRequest loginHandler = new LoginRequest(connectionSource);
                loginHandler.onRecive(message, thread);
            } else {
                Client client = new Client();
                client.setClientThread(thread);
                send(DefaultMessages.BAD_REQUEST + "", Constants.TCP, client);
            }*/

        }catch (Exception e){
            try {
                Client client = new Client();
                client.setClientThread(thread);
                send(Constants.BAD_REQUEST + "", Constants.TCP, client);
            }catch(Exception e1){
                Log.write(e);
            }
        }
    }

    @Override
    public void onRecive(String message, UDPServeThread thread) {

    }
}
