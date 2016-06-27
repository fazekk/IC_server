package handlers;

import com.j256.ormlite.dao.Dao;
import interfaces.RequestHandler;
import models.*;
import models.Character;

import java.util.List;

/**
 * Created by zipfs on 2015. 12. 29..
 */
public class ShopHandler extends RequestHandler {

    private List<Team> teams;
    private Dao<Team,String> teamDao;
    private Dao<UserHasTeam, String> userHasTeamsDao;
    private Dao<Character, String> characterDao;
    private Dao<Client,String> userDao;

    @Override
    public void onRecive(String message) {
        /*super.onRecive(message);
        Client user = Session.getUserWithSession(message[1]);
        try {
            if(Integer.parseInt(message[2]) == DefaultMessages.SHOP_BUY_TEAM) {
                Team selectedTeam = null;
                for (Team team : teams) {
                    if (team.getTeamID() == Integer.parseInt(message[3])) {
                        selectedTeam = team;
                        break;
                    }
                }
                List<UserHasTeam> userHasTeams = userHasTeamsDao.queryBuilder().where()
                        .eq(SystemUser.USER_ID, user.getUser().getUserID()).and().eq(Team.TEAM_ID, selectedTeam.getTeamID()).query();
                if (user.getUser().getMoney() >= selectedTeam.getPrice()
                        && userHasTeams.size() < Integer.parseInt(ServerVariables.getValue(Properties.PROP_TEAM_MAX_SIZE))) {
                    UserHasTeam userHasTeam = new UserHasTeam();
                    userHasTeam.setLevel(1);
                    userHasTeam.setTeamID(selectedTeam.getTeamID());
                    userHasTeam.setUserID(user.getUser().getUserID());

                    user.getUser().setMoney(user.getUser().getMoney() - selectedTeam.getPrice());
                    userDao.update(user);
                    userHasTeamsDao.create(userHasTeam);
                    send(DefaultMessages.SHOP_MESSAGE + ";" + DefaultMessages.SHOP_BUY_SUCCESSFUL + "");

                } else {
                    send(DefaultMessages.SHOP_MESSAGE + ";" + DefaultMessages.SHOP_CANT_BUY_THIS + "");
                }

            }else if(Integer.parseInt(message[2]) == DefaultMessages.SHOP_GET_ITEMS){
                    for(Team team : teams) {
                        List<Character> characters = characterDao.queryBuilder().where().eq(Character.CHARACTER_ID, team.getCharacterID()).query();
                        Character character = characters.get(0);
                        List<UserHasTeam> userHasTeams = userHasTeamsDao.queryBuilder().where().eq(Team.TEAM_ID, team.getTeamID())
                                .and().eq(SystemUser.USER_ID, user.getUser().getUserID()).query();
                        send(
                                DefaultMessages.SHOP_MESSAGE + ";" + DefaultMessages.SHOP_TEAM
                                        + ";" + team.toMessage()
                                        + ";" + userHasTeams.size()
                                        + ";" + character.toMessage());
                    }
            }else {
                    send(DefaultMessages.BAD_REQUEST+";");
            }
        } catch (Exception e) {
            Log.write(e);
        }*/
    }
    /*
    @Override
    public void send(String message) throws IOException {
        //super.send(message);
    }*/
}
