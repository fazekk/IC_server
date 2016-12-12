package main;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import constants.Properties;
import logger.Log;
import server.ServerVariables;

/**
 * Created by zipfs on 2016. 06. 27..
 */
public class Main {

    public static void main(String[] args) {
        ServerApplication.start();
        try {
            ConnectionSource connectionSource =
                    new JdbcConnectionSource(ServerVariables.getValue(Properties.PROP_DATABASE_STRING));
            TableCreator.createTable(connectionSource);
        }
        catch (Exception e)
        {
            Log.write(e);
        }

    }
}
