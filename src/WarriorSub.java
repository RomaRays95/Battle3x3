public class WarriorSub extends Hero {
    private int regen;

    @Override
    public int getRegen() {
        return regen;
    }

    public WarriorSub(String name, String name2) {
        super(name, name2);
        regen = 4 + rand.nextInt(2);
    }

    @Override
    public int attack() {
        super.health += regen;
        return super.attack();
    }

    @Override
    public void upLvl() {
        regen += 2;
        super.upLvl();
    }
}
