import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static StringBuilder logs = new StringBuilder();
    public static void main(String[] args) {
        Hero[] heroes1 = new Hero[3];
        Hero[] heroes2 = new Hero[3];

        heroes1[0] = new WarriorSub("Воин Джон", "воина Джона");
        heroes2[0] = new WarriorSub("Воин Ляпис", "воина Ляписа");
        heroes1[1] = new MageSub("Маг Бобби", "мага Бобби");
        heroes2[1] = new MageSub("Маг Петр", "мага Петра");
        heroes1[2] = new AssasinSub("Ассасин Герри", "ассасина Герри");
        heroes2[2] = new AssasinSub("Ассасин Владимир", "ассасина Владимира");
        statsOfComands(heroes1, heroes2);


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

    public static int findTarget(Hero[] x){
        Random rand = new Random();
        boolean targetDetermined = true;
        int choose = 0;
        if(!(x[0].isAlive() || x[1].isAlive() || x[2].isAlive())) targetDetermined = false;
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

    public static void statsOfComands (Hero[] a, Hero[] b){
        System.out.printf("\nКоманда Хиппи:\n%s: ХП= %d, Урон = %d, Реген = %d\n", a[0].getName(), a[0].getHealth(), a[0].getDamage(), a[0].getRegen());
        System.out.printf("%s: ХП= %d, Урон = %d, Мана = %d\n",a[1].getName(), a[1].getHealth(), a[1].getDamage(), a[1].getMana());
        System.out.printf("%s: ХП= %d, Урон = %d, Шанс Крита = %d \n\n",a[2].getName(), a[2].getHealth(), a[2].getDamage(), a[2].getKrit());
        wait(3);
        System.out.printf("Команда Богатырей:\n%s: ХП= %d, Урон = %d, Реген = %d\n",b[0].getName(), b[0].getHealth(), b[0].getDamage(), b[0].getRegen());
        System.out.printf("%s: ХП= %d, Урон = %d, Мана = %d\n",b[1].getName(), b[1].getHealth(), b[1].getDamage(), b[1].getMana());
        System.out.printf("%s: ХП= %d, Урон = %d, Шанс Крита = %d \n\n",b[2].getName(), b[2].getHealth(), b[2].getDamage(), b[2].getKrit());
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
//    auff-test
}
