public class AssasinSub extends Hero{
    private int krit;
    private int bonusDamage;

    public AssasinSub(String name, String name2) {
        super(name, name2);
        krit = 15 + rand.nextInt(5);
        bonusDamage = 0;
    }

    @Override
    public int attack() {
        int damageOfCurrentLvl = damage;
        int kritChance = rand.nextInt(100) + 1;
        bonusDamage = 0;
        if (kritChance <= krit) bonusDamage = (int) (damage * 0.5);
        Main.logs.append(String.format("<%s бьёт силой %d ", super.getName(), (damageOfCurrentLvl + bonusDamage)));
        currentLvl = lvl;
        exp += damage + bonusDamage;
        if (exp > 30 + (lvl * 10)) {
            exp -= 30 + (lvl * 10);
            upLvl();
            lvlAfterTurn = lvl;
        }
        return damageOfCurrentLvl + bonusDamage;
    }

    @Override
    public void upLvl() {
        super.upLvl();
        krit += 3;
    }

    @Override
    public int getKrit() {
        return krit;
    }
}
