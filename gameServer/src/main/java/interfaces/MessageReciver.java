package interfaces;

import com.j256.ormlite.support.ConnectionSource;
import thread.ClientThread;

import java.io.IOException;

/**
 * Created by zipfs on 2015. 12. 20..
 */
public class MessageReciver {

    private ClientThread thread;
    private ConnectionSource connectionSource;

    public MessageReciver(ConnectionSource connectionSource, ClientThread thread){
        this.thread = thread;
        this.connectionSource = connectionSource;
    }

    public void onRecive(String[] message){

    }

    public void send(String message) throws IOException {
        thread.send(message);
    }
}
