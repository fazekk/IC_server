package models;

/**
 * Created by Kesze on 2016.11.11..
 */
public class Player {

    private Client client;
    private SpawnPoint spawnPoint;
    private Vector3 position;
    private Long timeStamp;
    private int hp;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public SpawnPoint getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(SpawnPoint spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
