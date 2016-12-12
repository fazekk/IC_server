package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.CommunicationConstants;
import handlers.MoveRequest;
import logger.Log;
import models.*;
import responses.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kesze on 2010.01.12..
 */
public class Game extends Thread {

    private int gameID;
    private List<Player> inGameClients = new ArrayList<Player>();
    private List<SpawnPoint> spawnPoints;
    //
    // A játék elindulásáért felelős ha mennie kell akkor true ha nem akkor false
    //
    //
    private boolean run;
    private int clientLoaded = 0;

   /*public void init (List<Client> currentUsers, int gameID) {
        inGameClients = currentUsers; //�thelyezz�k a currentusersb�l a playereket
        this.gameID = gameID; //�tadjuk a gameID-t
        for(Client client : currentUsers)
        {
            client.setGameID(gameID); //be�ll�tjuk az adott usernek a gameid-j�t
        }

        run = true;
    }*/
   public void init (List<Client> currentUsers, int gameID, ConnectionSource connectionSource) {
       try{
           //
           // Csatlakozunk az adatbázishoz
           //
           Dao<SpawnPoint,String> spawnPointDao = DaoManager.createDao(connectionSource,SpawnPoint.class);
           Dao<Map,String> mapDao = DaoManager.createDao(connectionSource,Map.class);
           //
           // Kiszedi az összes adatot!? a map táblából
           //
           List<Map> maps = mapDao.queryForAll();
           Random r = new Random();
           int randomMapID = maps.get(r.nextInt(maps.size())).getId();
           //
           // Kiszedi a spawnpoint adatbázisból a random map idhez hozzárendelt spawnpointokat
           //
           spawnPoints = spawnPointDao.queryBuilder().where().eq(SpawnPoint.MAP_ID,randomMapID).query();

           this.gameID = gameID;
           int i = 0;
           for(Client client : currentUsers)
           {
               Player player = new Player();
               //
               // Client beállítás
               //
               player.setClient(client);
               //
               // Spawnpoint kiválasztása hozzárendelve a playerhez
               //
               player.setSpawnPoint(spawnPoints.get(i));
               //
               // Kezdő pozíció megadása
               //
               player.setPosition(new Vector3(spawnPoints.get(i).getX(),spawnPoints.get(i).getY(), spawnPoints.get(i).getZ()));
               //
               // ID megadása a playernek
               //
               player.setId(i);
               //
               // Megadjuk a player alap hp-ját
               //
               player.setHp(100);
               //
               // Hozzáadjuk az itt létrehozott tömbhöz a playerek adatait
               //
               inGameClients.add(player);
               LoadGameResponse response = new LoadGameResponse();
               //
               // MapID hozzáadása a LoadGameResponsenak
               //
               response.setMapID(randomMapID);
               response.setType(CommunicationConstants.LOAD_GAME_RESPONSE);
               Log.write(player.getClient().getUser().getName());
               player.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(response)); //kliensnek kiküldjük a kiválaszott map id-jét
               //Log.write("GameID:" + getGameID());
               i++;
           }

           Log.write("GameServerStarted!");
       }catch (Exception e){
           Log.write(e);
       }
   }

    public void clientLoaded(Client client){
        try {
            //
            // Számolja hány kliens töltött be
            //
            clientLoaded++;
            Log.write("ClientLoaded");
            //
            // Ha a betöltött kliensek száma megegyezik a tömbben lévő playerek számával
            // akkor belépünk az ifbe
            //
            if (clientLoaded == inGameClients.size()) {
                //
                // Foreach-el kiszedjük az éppa adott kliens playerét
                //
                for(Player player : inGameClients){
                    StartGameResponse response = new StartGameResponse();
                    List<Enemy> enemies = new ArrayList<Enemy>();
                    //
                    // Majd kiszedjük a playereket sorban
                    //
                    for(Player enemyPlayer : inGameClients){
                        //
                        // Ha a kiszedett player nem egyezik meg az előbb kuszedett playerrel
                        // akkor enemynek minősül
                        //
                        if(enemyPlayer != player){
                            Enemy enemy = new Enemy();
                            //
                            // Enemy beállítása az Eemy class alapján
                            //
                            enemy.setId(enemyPlayer.getId());
                            enemy.setPositionEnemy(enemyPlayer.getPosition());
                            //
                            // Hozzáadás az itt letrehozott enemy tömbhöz
                            //
                            enemies.add(enemy);
                        }
                    }
                    response.setType(CommunicationConstants.START_GAME_RESPONSE);
                    //
                    // Megadjuk a StartGameResponse-nak az enemy listánkat
                    //
                    response.setEnemyList(enemies);
                    //
                    // Megadjuk még neki az adott player pozícióját
                    //
                    response.setPosition(player.getPosition());
                    //
                    // Elküldjük a player kezdő pozícióját és az enemy helyzetét
                    //
                    player.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(response));

                }
                run = true;
                this.start();
            } else {
                InitResponse response = new InitResponse();
                response.setType(CommunicationConstants.INIT_RESPONSE);
                //response.setSuccess(true);
                client.getClientThread().send(new ObjectMapper().writeValueAsString(response));
            }
        }catch (Exception e){
            Log.write(e);
        }
    }
    public void moveRequest(Client client, Vector3 newPosition, Long timeStamp){

        Player sender = null;
        for(Player player : inGameClients){
            if(player.getClient() == client){
                sender = player;
                player.setPosition(newPosition);
                player.setTimeStamp(timeStamp);
                break;
            }

        }
        for(Player enemy : inGameClients){
            if(enemy != sender) {
                try {
                    MoveResponse moveResponse = new MoveResponse();
                    moveResponse.setType(CommunicationConstants.MOVE_RESPONSE);
                    moveResponse.setId(sender.getId());
                    moveResponse.setNewPosition(sender.getPosition());
                    moveResponse.setTimeStamp(sender.getTimeStamp());
                    enemy.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(moveResponse));
                } catch (Exception e) {
                    Log.write(e);
                }
            }
        }
    }

    public void shootRequest(Client client, int newhp, int dmg) {
        for(Player player : inGameClients) {
            if(player.getClient() != client){
                int currenthp = player.getHp() - dmg;
                player.setHp(currenthp);
                break;
            }
        }
        /*for(Player player : inGameClients) {
            if (player.getClient() != client) {
                client.getClientThread().send(new ObjectMapper().writeValueAsString(player));
            }
        }*/
    }

    private void sendPositions() {
        try {
            for (Player player : inGameClients) {
                for (Player enemy : inGameClients) {
                    if (player != enemy && player.getClient().isOnline()) {
                        MoveResponse moveResponse = new MoveResponse();
                        moveResponse.setType(CommunicationConstants.MOVE_RESPONSE);
                        moveResponse.setId(enemy.getId());
                        moveResponse.setNewPosition(enemy.getPosition());
                        player.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(moveResponse));
                    }
                }
            }
        }catch(Exception e){
            run =  false;
            GameManagger.gameEnded(this.gameID);
            for (Player player : inGameClients) {
                player.getClient().setGameID(0);
                EndGameResponse endResponse = new EndGameResponse();
                endResponse.setType(CommunicationConstants.END_GAME_RESPONSE);
                //endResponse.setTime(0);
                endResponse.setVictory(false);
                try {
                    if(player.getClient().isOnline()) {
                        player.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(endResponse));
                    }
                }catch (Exception ex){
                    Log.write(ex);
                }
            }
        }
    }


    @Override
    public void run() {
        super.run();
        while(run)
        {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                Log.write(e);
            }
        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public List<Player> getInGameClients() {
        return inGameClients;
    }

    public void setInGameClients(List<Player> inGameClients) {
        this.inGameClients = inGameClients;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
