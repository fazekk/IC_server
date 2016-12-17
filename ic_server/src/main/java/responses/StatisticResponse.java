package responses;

import com.j256.ormlite.stmt.query.In;
import models.Response;

/**
 * Created by kesze on 2016.12.16..
 */
public class StatisticResponse extends Response {
    private String name;
    private Integer kill;
    private Integer death;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKill() {
        return kill;
    }

    public void setKill(Integer kill) {
        this.kill = kill;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }
}
