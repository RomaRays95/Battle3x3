import java.util.Random;

public class Assasin {
    private int health;
    private int damage;
    private int lvl;
    private int exp;
    private int healthMax;
    private boolean alive;
    private int krit;
    private String name;
    private String name2;
    private int bonusDamage;
    private int currentLvl;
    private int lvlAfterTurn;
    Random rand = new Random();

    public Assasin(String name, String name2) {
        healthMax = 120 + rand.nextInt(20);
        health = healthMax - 10;
        damage = 15 + rand.nextInt(5);
        alive = true;
        krit = 15 + rand.nextInt(5);
        lvl = 1;
        this.name = name;
        this.name2 = name2;
        bonusDamage = 0;
    }

    public void receiveDamage(int i) {
        health -= i;
        if (health <= 0){
            alive = false;
            health = 0;
        }
        Main.logs.append(String.format("%s, у него осталось %d HP>", name2, health));
        if (health == 0) Main.logs.append(String.format("<%s убит!>", name));
    }

    public int attack () {
        int damageOfCurrentLvl = damage;
        int kritChance = rand.nextInt(100) + 1;
        bonusDamage = 0;
        if (kritChance <= krit) bonusDamage = (int) (damage * 0.5);
        Main.logs.append(String.format("<%s бьёт силой %d ", name, (damageOfCurrentLvl + bonusDamage)));
        currentLvl = lvl;
        exp += damage + bonusDamage;
        if (exp > 30 + (lvl * 10)) {
            exp -= 30 + (lvl * 10);
            upLvl();
            lvlAfterTurn = lvl;
        }
        return damageOfCurrentLvl + bonusDamage;
    }

    public void upLvl(){
        healthMax += 10;
        health += (healthMax * 0.15);
        krit += 3;
        damage += 3;
        lvl ++;
        if (health > healthMax) health = healthMax;
    }

    public void isUpLvl(){
        if (lvlAfterTurn > currentLvl) {
            Main.logs.append(String.format("<%s апнул уровень, теперь у него %d lvl>", name, lvl));
        }
    }

    public void upHealth(int i){
        health += i;
        if (health > healthMax) health = healthMax;
        Main.logs.append(String.format("HP союзника %s, теперь у него %d HP>", name2, health));
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getLvl() {
        return lvl;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getKrit() {
        return krit;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }
}
