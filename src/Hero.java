import java.util.Random;

public class Hero {
    protected int health;
    protected int damage;
    private String name;
    private String name2;
    protected int lvl;
    protected int exp;
    protected int healthMax;
    private boolean alive;
    protected int currentLvl;
    protected int lvlAfterTurn;
    Random rand = new Random();

    public Hero (String name, String name2){
        alive = true;
        lvl = 1;
        exp = 0;
        healthMax = 120 + rand.nextInt(20);
        health = healthMax - 10;
        damage = 18 + rand.nextInt(5);
        this.name = name;
        this.name2 = name2;
    }

    public void receiveDamage(int i){
        health -= i;
        if (health <= 0) {
            alive = false;
            health = 0;
        }
        Main.logs.append(String.format("%s, у него осталось %d HP>", name2, health));
        if (health == 0) Main.logs.append(String.format("<%s убит!>", name));
    }

    public void upLvl(){
        healthMax += 15;
        health += (healthMax * 0.1);
        damage += 3;
        lvl ++;
        if (health > healthMax) health = healthMax;
    }

    public void upHealth(int i){
        health += i;
        if (health > healthMax) health = healthMax;
        Main.logs.append(String.format("HP союзника %s, теперь у него %d HP>", name2, health));
    }

    public int attack (){
        int damageOfCurrentLvl = damage;
        currentLvl = lvl;
        exp += damage;
        Main.logs.append(String.format("<%s бьёт силой %d ", name, damageOfCurrentLvl));
        if (health > healthMax) health = healthMax;
        if (exp > 30 + (lvl * 10)){
            exp -= 30 + (lvl * 10);
            upLvl();
            lvlAfterTurn = lvl;
        }
        return damageOfCurrentLvl;
    }

    public void isUpLvl(){
        if (lvlAfterTurn > currentLvl) {
            Main.logs.append(String.format("<%s апнул уровень, теперь у него %d lvl>", name, lvl));
        }
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public int getLvl() {
        return lvl;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getRegen() {
        return 0;
    }

    public int getMana() {
        return 0;
    }

    public int getKrit() {
        return 0;
    }

    public void magic(Hero[] x){
    }
}
