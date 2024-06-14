package Test.ThreadTest5;

import java.util.ArrayList;
import java.util.Collections;

public class PotUtil {
    private PotUtil(){};

    public static ArrayList<Integer> initPot(){
        ArrayList<Integer> pot = new ArrayList<>();
        Collections.addAll(pot,10,5,20,50,100,200,500,800,2,80,300,700);
        return pot;
    }
}
