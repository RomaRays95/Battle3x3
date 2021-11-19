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


        while((heroes1[0].isAlive() || heroes1[1].isAlive() || heroes1[2].isAlive()) && (heroes2[0].isAlive() || heroes2[1].isAlive() || heroes2[2].isAlive())){
            turn(0, heroes1, heroes2);
            turn(0, heroes2, heroes1);
            if (heroes1[1].isAlive()) heroes1[1].magic(heroes1);
            if (heroes2[1].isAlive()) heroes2[1].magic(heroes2);
            turn(1, heroes1, heroes2);
            turn(1, heroes2, heroes1);
            turn(2, heroes1, heroes2);
            turn(2, heroes2, heroes1);
        }
        if ((heroes1[0].isAlive() || heroes1[1].isAlive() || heroes1[2].isAlive())) logs.append("<Хиппи победили!>");
        else logs.append("<Пацаны одержали верх!!!>");
        outLogs(3);
    }

    private static void turn(int whichTurn, Hero[] a, Hero[] b){
        int target = findTarget(b);
        if (target != 10){
            if (a[whichTurn].isAlive()){
                b[target].receiveDamage(a[whichTurn].attack());
                a[whichTurn].isUpLvl();
            }
        }
    }

    public static int findTarget(Hero[] x){
        Random rand = new Random();
        boolean targetDetermined = true;
        int choose = 10;
        if(!(x[0].isAlive() || x[1].isAlive() || x[2].isAlive())) targetDetermined = false;
        while (targetDetermined) {
            choose = rand.nextInt(3);
            if (x[choose].isAlive()){
                targetDetermined = false;
            }
            else {
                targetDetermined = true;
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
}
