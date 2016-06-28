package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

/**
 * Created by zipfs on 2015. 12. 24..
 */
@DatabaseTable(tableName = "character")
public class Character {

    public static final String CHARACTER_ID = "characterID";
    public static final String CHARACTER_NAME = "characterName";
    public static final String CHARACTER_STAMINA = "characterStamina";
    public static final String CHARACTER_INTELLIGENCE = "characterIntelligence";
    public static final String CHARACTER_ARMOR = "characterArmor";
    public static final String CHARACTER_STRENGTH = "characterStrength";
    public static final String CHARACTER_MAGIC_RESIST = "characterMagicResist";
    public static final String CHARACTER_SPIRIT = "characterSpirit";

    @DatabaseField(columnName = CHARACTER_ID, generatedId = true)
    private int characterID;

    @DatabaseField(columnName = CHARACTER_NAME)
    private String name;

    @DatabaseField(columnName = CHARACTER_STAMINA)
    private int stamina;

    @DatabaseField(columnName = CHARACTER_INTELLIGENCE)
    private int intelligence;

    @DatabaseField(columnName = CHARACTER_ARMOR)
    private int armor;

    @DatabaseField(columnName = CHARACTER_MAGIC_RESIST)
    private int magicResist;

    @DatabaseField(columnName = CHARACTER_STRENGTH)
    private int strength;

    @DatabaseField(columnName = CHARACTER_SPIRIT)
    private int spirit;

    public int getCharacterID() {
        return characterID;
    }

    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist = magicResist;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }
}
