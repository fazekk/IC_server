package interfaces;

import com.j256.ormlite.support.ConnectionSource;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import constants.Constants;
import models.Client;
import models.request.Request;
import thread.ClientThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by sinemissione on 2016.06.24..
 */
public class MessageHandler {

    private ConnectionSource connectionSource;

    public MessageHandler(ConnectionSource connectionSource){
        this.connectionSource = connectionSource;
    }

    public void onRecive(String request){

    }

    public void send(String message, int socketType, Client client) throws IOException {
        if(socketType == Constants.TCP) {
            client.getClientThread().send(message);
        }else if(socketType == Constants.UDP){
            String udpMessage = message + "\n";
            client.getUdpSocket().send(new DatagramPacket(udpMessage.getBytes(),0, udpMessage.getBytes().length));
        }
    }
}
