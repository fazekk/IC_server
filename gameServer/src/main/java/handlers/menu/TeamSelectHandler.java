package handlers.menu;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import constants.DefaultMessages;
import interfaces.MessageReciver;
import helpers.Session;
import logger.Log;
import models.*;
import models.Character;
import thread.ClientThread;

import java.io.IOException;
import java.util.List;

/**
 * Created by zipfs on 2015. 12. 29..
 */
public class TeamSelectHandler extends MessageReciver {

    private List<UserHasTeam> userTeams;
    private Dao<UserHasTeam, String> userHasTeamDao;
    private Dao<Team, String> teamDao;
    private Dao<models.Character, String> characterDao;

    public TeamSelectHandler(ConnectionSource connectionSource, ClientThread thread) {
        super(connectionSource, thread);
        try {
            userHasTeamDao = DaoManager.createDao(connectionSource, UserHasTeam.class);
            teamDao = DaoManager.createDao(connectionSource, Team.class);
            characterDao = DaoManager.createDao(connectionSource, Character.class);
        }catch (Exception e){
            Log.write(e);
        }

    }

    @Override
    public void onRecive(String[] message) {
        super.onRecive(message);
        try {
            Client user = Session.getUserWithSession(message[1]);
            userTeams = userHasTeamDao.queryBuilder().where().eq(SystemUser.USER_ID, user.getUser().getUserID()).query();
            if(Integer.parseInt(message[2]) == DefaultMessages.SELECT_TEAM) {
                Team selectedTeam = null;
                if (user.getSelectedTeams().size() < 6) {
                    for (UserHasTeam userHasTeam : userTeams) {
                        if (userHasTeam.getUserTeamID() == Integer.parseInt(message[3])) {
                            selectedTeam = teamDao.queryBuilder().where().eq(Team.TEAM_ID, userHasTeam.getTeamID()).query().get(0);
                        }
                    }
                } else {
                    send(DefaultMessages.SELECT_FAILED + "");
                    return;
                }
                user.addTeam(selectedTeam);
                send(DefaultMessages.SELECT_SUCCESSFUL + "");
            }else if(Integer.parseInt(message[2]) == DefaultMessages.GET_AVAILABLE_TEAMS){
                for(UserHasTeam uTeam : userTeams){
                    List<Team> team = teamDao.queryBuilder().where().eq(Team.TEAM_ID, uTeam.getTeamID()).query();
                    List<Character> character = characterDao.queryBuilder().where()
                            .eq(Character.CHARACTER_ID, team.get(0).getCharacterID()).query();
                    send(DefaultMessages.TEAM_SELECT+";"+DefaultMessages.TEAM_LIST
                            +";"+team.get(0).toMessage()+";"+character.get(0).toMessage()+";"+uTeam.getLevel() + ";" + uTeam.getUserTeamID());
                }
            }
        }catch (Exception e){
            Log.write(e);
        }
    }

    @Override
    public void send(String message) throws IOException {
        super.send(message);
    }
}
