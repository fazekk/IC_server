package game;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.DefaultMessages;
import helpers.Session;
import logger.Log;
import models.Map;
import models.Client;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by zipfs on 2015. 12. 20..
 * GameHandler script
 */
public class GameHandler{

    private List<Client> users;
    private ConnectionSource connectionSource;
    private Map map;

    public GameHandler(ConnectionSource connectionSource, List<Client> users) {
        this.connectionSource = connectionSource;
        this.users = users;
        try {
            Dao<Map, String> mapDao = DaoManager.createDao(connectionSource, Map.class);
            List<Map> maps = mapDao.queryBuilder().where().eq(Map.MAP_MAX_PLAYER, users.get(0).getSelectedGameType()).query();
            Random r = new Random();

            map = maps.get(r.nextInt(maps.size()-1));

            for (Client user : users) {
                user.getClientThread().getRouter().setGameHandler(this);
                user.setStatus(Client.STATUS_IN_GAME);
                users.add(user);

                send(DefaultMessages.SEARCH + ";" + DefaultMessages.GAME_FOUND + ";" + map.getMapName(), user);
            }
        }catch (Exception e){
            Log.write(e);
        }
    }

    public void recive(String[] data) throws Exception {
        updateClients();
        Client user = Session.getUserWithSession(data[1]);
        if(Integer.parseInt(data[2]) == DefaultMessages.READY_FOR_GAME){
            user.setStatus(Client.STATUS_REDY_FOR_GAME);
            for(Client sysUser : users){
                if(sysUser.getStatus()!=Client.STATUS_REDY_FOR_GAME){

                    return;
                }
            }
            SpawnHandler spawnHandler = new SpawnHandler(connectionSource, users, map);
            spawnHandler.sendSpawnPoints();
        }
    }

    private void updateClients() {
        for(Client user : users){
            user.setClientThread(Session.getUserWithSession(user.getSession()).getClientThread());
            user.getClientThread().getRouter().setGameHandler(this);
        }
    }

    private void send(String data, Client user){
        try {
            user.getClientThread().send(data);
        } catch (IOException e) {
            Log.write(e);
        }
    }
}
