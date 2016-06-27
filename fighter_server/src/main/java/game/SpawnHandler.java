package game;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.Constants;
import constants.DefaultMessages;
import models.Map;
import models.SpawnPoint;
import models.Client;

import java.util.List;

/**
 * Created by zipfs on 2016. 01. 16..
 */
public class SpawnHandler {

    private ConnectionSource connectionSource;
    private List<Client> users;
    private Map map;
    private List<SpawnPoint> spawnPoints;

    public SpawnHandler(ConnectionSource connectionSource, List<Client> users, Map map) {
        this.connectionSource = connectionSource;
        this.users = users;
        this.map = map;
    }

    public void sendSpawnPoints() throws Exception{
        Dao<SpawnPoint, String> spawnPointDao = DaoManager.createDao(connectionSource, SpawnPoint.class);

        spawnPoints = spawnPointDao.queryBuilder().where().eq(Map.MAP_ID, map.getMapID()).query();
        int i=0;
        for(Client user : users){
            user.getClientThread().send(DefaultMessages.GAME_MESSAGE+";"+DefaultMessages.GAME_READY);
            SpawnPoint tempSpawnPoint = spawnPoints.get(i);
            for(int j = 0;j<user.getSelectedTeams().size();j++) {
                tempSpawnPoint.setSpawnX(tempSpawnPoint.getSpawnX() + j*Constants.TEAM_OFFSET);
                user.getClientThread().send(DefaultMessages.GAME_MESSAGE + ";" + DefaultMessages.SPAWN_TEAM + ";" +tempSpawnPoint.toMessage());
            }
            i++;
        }
    }
}
