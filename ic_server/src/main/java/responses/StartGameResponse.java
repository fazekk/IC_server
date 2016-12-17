package responses;

import models.Enemy;
import models.Response;
import models.Vector3;

import java.util.List;

/**
 * Created by kesze on 2016.12.02..
 */
public class StartGameResponse extends Response {
    private List<Enemy> enemyList;
    private Vector3 position;

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
