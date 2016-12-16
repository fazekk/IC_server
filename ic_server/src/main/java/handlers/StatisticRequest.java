package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import constants.CommunicationConstants;
import helpers.Session;
import interfaces.RequestHandler;
import logger.Log;
import models.Client;
import models.Statistics;
import responses.StatisticResponse;

import java.util.List;


/**
 * Created by kesze on 2016.12.16..
 */
public class StatisticRequest extends RequestHandler {
    private String name;
    private String kill;
    private String death;

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        try {
            Dao<Statistics, String> statisticsDao = DaoManager.createDao(connectionSource, Statistics.class);
            List<Statistics> statistics = statisticsDao.queryForAll();
            Client client = Session.getUserWithSession(session); //sessionnel ell?tott cliensek
            //int faszombele = client.getUser().getUserID();

            for(int i=0; i<statistics.size(); i++) {
                if (client.getUser().getUserID() == statistics.get(i).getUserID()) {
                    StatisticResponse statisticResponse = new StatisticResponse();
                    statisticResponse.setName(client.getUser().getName());
                    statisticResponse.setKill(statistics.get(i).getKill());
                    statisticResponse.setDeath(statistics.get(i).getDeath());
                    statisticResponse.setType(CommunicationConstants.STATISTIC_RESPONSE);
                    client.getClientThread().send(new ObjectMapper().writeValueAsString(statisticResponse));
                }
            }

        } catch (Exception e){
            Log.write(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKill() {
        return kill;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }
}
