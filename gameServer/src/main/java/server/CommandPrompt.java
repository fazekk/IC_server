package server;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import constants.Commands;
import constants.Properties;
import helpers.Games;
import logger.Log;
import models.Character;
import models.*;
import thread.ServeThread;

/**
 * Created by zipfs on 2016. 01. 11..
 * Handle the user commands
 */
public class CommandPrompt {

    private ServeThread serveThread;
    private String confName;

    public CommandPrompt(ServeThread serveThread, String confName) {
        this.serveThread = serveThread;
        this.confName = confName;
        System.out.println("Type the commands!");
    }

    /*
    Handle the user commands
     */
    public void commandReceived(String[] inputData) throws Exception{
        if(inputData[0].equals(Commands.START)){
            startServer(ServerVariables.getValue(Properties.PROP_DATABASE_STRING)
                    , Long.parseLong(ServerVariables.getValue(Properties.PROP_SERVE_SLEEP))
                    , Long.parseLong(ServerVariables.getValue(Properties.PROP_CLIENT_SLEEP))
                    , Integer.parseInt(ServerVariables.getValue(Properties.PROP_PORT)), serveThread);
        }else if(inputData[0].equals(Commands.EXIT)){
            if(serveThread != null && serveThread.isRun()) {
                stopServer(serveThread);
            }
        }else if(inputData.length>1 && inputData[0].equals(Commands.SET)){
            if(inputData[1].equals(Commands.SET_TIMEOUT)){
                ServerVariables.setKey(Properties.PROP_TIME_OUT, inputData[2], Boolean.parseBoolean(inputData[3]));
            }else if(inputData[1].equals(Commands.SET_CLIENT_SLEEP)){
                ServerVariables.setKey(Properties.PROP_CLIENT_SLEEP,inputData[2], Boolean.parseBoolean(inputData[3]));
            }else if(inputData[1].equals(Commands.SET_SERVE_SLEEP)){
                ServerVariables.setKey(Properties.PROP_SERVE_SLEEP,inputData[2], Boolean.parseBoolean(inputData[3]));
            }else if(inputData[1].equals(Commands.SET_PORT)){
                ServerVariables.setKey(Properties.PROP_PORT,inputData[2], Boolean.parseBoolean(inputData[3]));
            }else if(inputData[1].equals(Commands.SET_DATABASE_STRING)){
                ServerVariables.setKey(Properties.PROP_DATABASE_STRING,inputData[2], Boolean.parseBoolean(inputData[3]));
            }else{
                writeUnknownMessage();
            }
            ServerVariables.writeConfigToFile();
        }else if(inputData[0].equals(Commands.HELP)){
            writeHelpMessage();
        }else if(inputData[0].equals(Commands.WRITE_CONFIG)){
            Log.write(ServerVariables.getFullConfigAsString());
        }else{
            writeUnknownMessage();
        }
    }

    /*
    Unknown command typed
     */
    private void writeUnknownMessage(){
        System.out.println("Unknown command or bad syntax! type '"+Commands.HELP+"' to help!");
    }

    /*
    Write a help dialog to console
     */
    private void writeHelpMessage(){
        System.out.println("Default commands: ");
        System.out.println("HELP: "+Commands.HELP);
        System.out.println("START: " + Commands.START);
        System.out.println("EXIT: " + Commands.EXIT);
        System.out.println("Variable setup (Must set before start!): " + Commands.SET + " variables:");
        System.out.println("----------------------------------------------------------");
        System.out.println("    SET : " + Commands.SET_TIMEOUT + " 'time out time (0 is no time out!)'");
        System.out.println("    SET : " + Commands.SET_DATABASE_STRING + " 'database string'");
        System.out.println("    SET : " + Commands.SET_SERVE_SLEEP + " 'serve sleep'");
        System.out.println("    SET : " + Commands.SET_CLIENT_SLEEP + " 'client sleep'");
        System.out.println("    SET : " + Commands.SET_PORT + " 'port'");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Variables saved in config file saved on modify! config name: " + confName);
    }

    /*
    Start the server
     */
    private void startServer(String databaseUrl
            , long serveSleep, long clientSleep, int port, ServeThread serveThread) throws Exception{


        ConnectionSource connectionSource =
                new JdbcConnectionSource(databaseUrl);
        try {
            TableUtils.createTableIfNotExists(connectionSource, SystemUser.class);
            TableUtils.createTableIfNotExists(connectionSource, Team.class);
            TableUtils.createTableIfNotExists(connectionSource, Character.class);
            TableUtils.createTableIfNotExists(connectionSource, UserHasTeam.class);
            TableUtils.createTableIfNotExists(connectionSource, SpawnPoint.class);
            TableUtils.createTableIfNotExists(connectionSource, Map.class);
            TableUtils.createTableIfNotExists(connectionSource, Result.class);

            serveThread.Init(port, connectionSource, serveSleep, clientSleep);
            Games.init();
            serveThread.start();
            Log.write("Running");
        }catch (Exception e){
            Log.write("Unable to connect to database, check you created correctly and db user can connect to it!");
            Log.write(e);
        }
    }

    /*
    Stop the server
     */
    private void stopServer(ServeThread serveThread){
        serveThread.shutDown();
        ServerVariables.shutDown();
        Log.write("Server shutdown!");
        Log.shutDown();
    }
}
