package handlers;

import models.Client;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Kesze on 2016.08.27..
 */
public class MatchQueue {
    private static LinkedBlockingQueue<Client> waitingPlayer=new LinkedBlockingQueue<Client>();
    public static void addClient(Client client){
        waitingPlayer.add(client);
    }
    public static List<Client> getPlayers(int num) {
        List<Client> clients = new ArrayList<Client>(); //num az egy helyfoglal� m�shol kap �rt�ket
        if (num <= waitingPlayer.size()) {
            for (int i = 0; i < num; i++) {
                clients.add(waitingPlayer.poll());
            }
            return clients;
        }
        else{
            return null;
        }
    }
    public static int waitingPlayersNumber(){
        return waitingPlayer.size();
    }
    public static void removePlayer(Client client){
        if(waitingPlayer.contains(client))
        waitingPlayer.remove(client);
    }
}
