import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static StringBuilder logs = new StringBuilder();
    public static void main(String[] args) {

        Warrior warrior1 = new Warrior("Воин Джон", "воина Джона");
        Warrior warrior2 = new Warrior("Воин Ляпис", "воина Ляписа");
        Mage mage1 = new Mage("Маг Бобби", "мага Бобби");
        Mage mage2 = new Mage("Маг Петр", "мага Петра");
        Assasin ass1 = new Assasin("Ассасин Герри", "ассасина Герри");
        Assasin ass2 = new Assasin("Ассасин Владимир", "ассасина Владимира");
        statsOfComands(warrior1, warrior2, mage1, mage2, ass1, ass2);


        while((warrior1.isAlive() || mage1.isAlive() || ass1.isAlive()) && (warrior2.isAlive() || mage2.isAlive() || ass2.isAlive())){
            turn(1, warrior1, warrior2, mage1, mage2, ass1, ass2);
            turn(1, warrior2, warrior1, mage2, mage1, ass2, ass1);
            if (mage1.isAlive()) mage1.magic(warrior1, mage1, ass1);
            if (mage2.isAlive()) mage2.magic(warrior2, mage2, ass2);
            turn(2, warrior1, warrior2, mage1, mage2, ass1, ass2);
            turn(2, warrior2, warrior1, mage2, mage1, ass2, ass1);
            turn(3, warrior1, warrior2, mage1, mage2, ass1, ass2);
            turn(3, warrior2, warrior1, mage2, mage1, ass2, ass1);
        }
        if ((warrior1.isAlive() || mage1.isAlive() || ass1.isAlive())) logs.append("<Хиппи победили!>");
        else logs.append("<Пацаны одержали верх!!!>");
        outLogs(3);
    }

    private static void turn(int whichTurn, Warrior xa, Warrior xb, Mage ya, Mage yb, Assasin za, Assasin zb){
        int target = findTarget(xb, yb, zb);
        if ((whichTurn == 1) && (xa.isAlive())){
            if (target == 1) xb.receiveDamage(xa.attack());
            if (target == 2) yb.receiveDamage(xa.attack());
            if (target == 3) zb.receiveDamage(xa.attack());
            xa.isUpLvl();
        }
        if ((whichTurn == 2) && (ya.isAlive())){
            if (target == 1) xb.receiveDamage(ya.attack());
            if (target == 2) yb.receiveDamage(ya.attack());
            if (target == 3) zb.receiveDamage(ya.attack());
            ya.isUpLvl();
        }
        if ((whichTurn == 3) && (za.isAlive())){
            if (target == 1) xb.receiveDamage(za.attack());
            if (target == 2) yb.receiveDamage(za.attack());
            if (target == 3) zb.receiveDamage(za.attack());
            za.isUpLvl();
        }
    }

    public static int findTarget(Warrior x, Mage y, Assasin z){
        Random rand = new Random();
        boolean targetDetermined = true;
        int choose = 0;
        if(!(x.isAlive() || y.isAlive() || z.isAlive())) targetDetermined = false;
        while (targetDetermined) {
            choose = rand.nextInt(3) + 1;
            if (choose == 1){
                if (x.isAlive()){
                    targetDetermined = false;
                } else {
                    targetDetermined = true;
                }
            } else if (choose == 2){
                if (y.isAlive()){
                    targetDetermined = false;
                } else {
                    targetDetermined = true;
                }
            } else {
                if (z.isAlive()){
                    targetDetermined = false;
                } else {
                    targetDetermined = true;
                }
            }
        }
        return choose;
    }

    public static void statsOfComands (Warrior xa, Warrior xb, Mage ya, Mage yb, Assasin za, Assasin zb){
        System.out.printf("\nКоманда Хиппи:\n%s: ХП= %d, Урон = %d, Реген = %d\n", xa.getName(), xa.getHealth(), xa.getDamage(), xa.getRegen());
        System.out.printf("%s: ХП= %d, Урон = %d, Мана = %d\n",ya.getName(), ya.getHealth(), ya.getDamage(), ya.getMana());
        System.out.printf("%s: ХП= %d, Урон = %d, Шанс Крита = %d \n\n",za.getName(), za.getHealth(), za.getDamage(), za.getKrit());
        wait(3);
        System.out.printf("Команда Богатырей:\n%s: ХП= %d, Урон = %d, Реген = %d\n",xb.getName(), xb.getHealth(), xb.getDamage(), xb.getRegen());
        System.out.printf("%s: ХП= %d, Урон = %d, Мана = %d\n",yb.getName(), yb.getHealth(), yb.getDamage(), yb.getMana());
        System.out.printf("%s: ХП= %d, Урон = %d, Шанс Крита = %d \n\n",zb.getName(), zb.getHealth(), zb.getDamage(), zb.getKrit());
        wait(3);
    }

    public static void outLogs(int t){
        Pattern p = Pattern.compile("<.*?>");
        Matcher m = p.matcher(logs);
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            System.out.println(logs.substring(start+1, end-1));
            wait(t);
        }
    }

    public static void wait(int t){
        try{
            Thread.sleep(t * 1000);
        }catch(InterruptedException ex){
        }
    }
}
