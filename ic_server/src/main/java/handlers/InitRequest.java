package handlers;

import controllers.Game;
import controllers.GameManagger;
import helpers.Session;
import interfaces.RequestHandler;
import models.Client;


/**
 * Created by Kesze on 2016.11.01..
 */
public class InitRequest extends RequestHandler {

    @Override
    public void onRecive(String request) {
        super.onRecive(request);
        Client client = Session.getUserWithSession(session); //sessionnel ell�tott cliensek
        Game game = GameManagger.getGame(client.getGameID()); //game lek�r�s
        game.clientLoaded(client);

        /*client.getUser().getName(); //lek�rni a beloggolt user nev�t

        try {
            Dao<SpawnPoint, String> userDao = DaoManager.createDao(connectionSource, SpawnPoint.class);
            List<SpawnPoint> spawnPoints = userDao.queryForAll();
            //Dao<class n�v, String> adott n�v = DaoManager.createDao(connectionSource, class n�v;
            //List<class n�v> p�ld�nyos�tva = adott n�v.queryForAll();
            //database...
            for(SpawnPoint spawnPoint : spawnPoints)
            {
                Log.write(spawnPoint.getId()+"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


    }

}
