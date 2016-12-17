package models;

/**
 * Created by Kesze on 2016.11.11..
 */
public class Player {

    private Client client;
    private SpawnPoint spawnPoint;
    private Vector3 position;
    private Long timeStamp;

    private int kill;
    private int death;

    private float rotation;
    private float vertical;
    private float horizontal;
    private boolean lshift;
    private boolean space;
    private boolean lctrl;

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

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getDeath() {
        return death;
    }

    public float getVertical() {
        return vertical;
    }

    public void setVertical(float vertical) {
        this.vertical = vertical;
    }

    public float getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(float horizontal) {
        this.horizontal = horizontal;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean isLshift() {
        return lshift;
    }

    public void setLshift(boolean lshift) {
        this.lshift = lshift;
    }

    public boolean isSpace() {
        return space;
    }

    public void setSpace(boolean space) {
        this.space = space;
    }

    public boolean isLctrl() {
        return lctrl;
    }

    public void setLctrl(boolean lctrl) {
        this.lctrl = lctrl;
    }
}
