package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.CommunicationConstants;
import handlers.MoveRequest;
import handlers.ShootRequest;
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
                            // Enemy beállítása az Enemy class alapján
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
                clientLoaded=0;
                this.start();
            } else {
                Response response = new Response();
                response.setType(CommunicationConstants.INIT_RESPONSE);
                //response.setSuccess(true);
                client.getClientThread().send(new ObjectMapper().writeValueAsString(response));
            }
        }catch (Exception e){
            Log.write(e);
        }
    }
    public void moveRequest(Client client, MoveRequest request){

        for(Player player : inGameClients){
            if(player.getClient() == client){
                player.setPosition(request.getNewPosition());
                player.setTimeStamp(request.getTimeStamp());
                player.setRotation(request.getRotation());
                player.setHorizontal(request.getHorizontal());
                player.setVertical(request.getVertical());
                player.setLshift(request.isLshift());
                player.setSpace(request.isSpace());
                player.setLctrl(request.isLctrl());
                break;
            }

        }

    }

    public Player getPlayerWithID(int id) {
        for(Player player : inGameClients) {
            if(player.getId() == id) {
                return player;
            }
        }
        Log.write("Player not found");
        return null;
    }

    public void shootRequest(ShootRequest req, Client client) {

        Player shootedPlayer = getPlayerWithID(req.getWoundedID());
        int newhp = shootedPlayer.getHp() - req.getDmg();
        if(newhp <= 0) {
            for(Player player : inGameClients) {
                if(client == player.getClient()) {
                    player.setKill(player.getKill()+1);
                }
            }
            shootedPlayer.setDeath(shootedPlayer.getDeath()+1);
        }
        shootedPlayer.setHp(newhp);
        Log.write("" + newhp);

        try {
            ShootResponse shootResponse = new ShootResponse();
            shootResponse.setNewhp(newhp);
            shootResponse.setType(CommunicationConstants.SHOOT_RESPONSE);
            shootedPlayer.getClient().getClientThread().send(new ObjectMapper().writeValueAsString(shootResponse));
        } catch (Exception e)  {
            Log.write(e);
        }
    }

    private void sendPositions() {
        for(int i = 0; i<inGameClients.size(); i++) {
            for (int j=0; j<inGameClients.size(); j++) {
                if (inGameClients.get(i) != inGameClients.get(j)) {
                    try {
                        MoveResponse moveResponse = new MoveResponse();
                        moveResponse.setType(CommunicationConstants.MOVE_RESPONSE);
                        moveResponse.setId(inGameClients.get(j).getId());
                        moveResponse.setNewPosition(inGameClients.get(j).getPosition());
                        moveResponse.setTimeStamp(inGameClients.get(j).getTimeStamp());
                        moveResponse.setRotation(inGameClients.get(j).getRotation());
                        moveResponse.setVertical(inGameClients.get(j).getVertical());
                        moveResponse.setHorizontal(inGameClients.get(j).getHorizontal());
                        moveResponse.setLshift(inGameClients.get(j).isLshift());
                        moveResponse.setSpace(inGameClients.get(j).isSpace());
                        moveResponse.setLctrl(inGameClients.get(j).isLctrl());
                        inGameClients.get(i).getClient().getClientThread().send(new ObjectMapper().writeValueAsString(moveResponse));
                    } catch (Exception e) {
                        Log.write(e);
                        //inGameClients.remove(i);
                    }
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
                sendPositions();
                sleep(30);
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
