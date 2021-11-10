import java.util.Random;

public class Mage {
    private int health;
    private int damage;
    private String name;
    private String name2;
    private int lvl;
    private int exp;
    private int healthMax;
    private boolean alive;
    private int mana;
    private int manaRegen;
    private int skillUpHealth;
    private int currentLvl;
    private int lvlAfterTurn;
    Random rand = new Random();

    public Mage(String name, String name2) {
        healthMax = 110 + rand.nextInt(20);
        health = healthMax - 10;
        damage = 18 + rand.nextInt(3);
        mana = 10 + rand.nextInt(15);
        manaRegen = 23 + rand.nextInt(3);
        skillUpHealth = 25;
        alive = true;
        lvl = 1;
        this.name = name;
        this.name2 = name2;
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

    public void upLvl(){
        healthMax += 10;
        health += (healthMax * 0.1);
        mana += 5;
        manaRegen += 3;
        damage += 3;
        skillUpHealth += 4;
        lvl ++;
        if (health > healthMax) health = healthMax;
    }

    public int attack (){
        int damageOfCurrentLvl = damage;
        currentLvl = lvl;
        exp += damage;
        mana += manaRegen;
        Main.logs.append(String.format("<%s бьёт силой %d ", name, damageOfCurrentLvl));
        if (exp > 20 + (lvl * 10)){
            exp -= 20 + (lvl * 10);
            upLvl();
            lvlAfterTurn = lvl;
        }
        return damageOfCurrentLvl;
    }

    public void magic(Warrior x, Mage y, Assasin z){
        int target = Main.findTarget(x, y, z);
        int chooseSkill = rand.nextInt(2) + 1;
        if ((target == 1) && (chooseSkill == 1) && (mana >= 25 + (lvl * 3))) x.upHealth(heal());
        if ((target == 2) && (chooseSkill == 1) && (mana >= 25 + (lvl * 3))) y.upHealth(heal());
        if ((target == 3) && (chooseSkill == 1) && (mana >= 25 + (lvl * 3))) z.upHealth(heal());
        if ((target == 1) && (chooseSkill == 2) && (mana >= 30 + (lvl * 2))){
            x.upLvl();
            skillUpLvl();
            Main.logs.append(String.format("союзника %s, теперь у него %d lvl>", x.getName2(), x.getLvl()));
        }
        if ((target == 2) && (chooseSkill == 2) && (mana >= 30 + (lvl * 2))){
            y.upLvl();
            skillUpLvl();
            Main.logs.append(String.format("самому себе, теперь у него %d lvl>", y.getLvl()));
        }
        if ((target == 3) && (chooseSkill == 2) && (mana >= 30 + (lvl * 2))){
            z.upLvl();
            skillUpLvl();
            Main.logs.append(String.format("союзника %s, теперь у него %d lvl>", z.getName2(), z.getLvl()));
        }


    }

    private int heal(){
        mana -= 25 + (lvl * 3);
        Main.logs.append(String.format("<%s лечит на %d ", name, skillUpHealth));
        return skillUpHealth;
    }

    private void skillUpLvl (){
        mana -= 30 + (lvl * 2);
        Main.logs.append(String.format("<%s поднимает уровень ", name));
    }

    public void upHealth(int i){
        health += i;
        if (health > healthMax) health = healthMax;
        Main.logs.append(String.format("HP самого себя>", name2, health));
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

    public int getLvl() {
        return lvl;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getMana() {
        return mana;
    }
}
