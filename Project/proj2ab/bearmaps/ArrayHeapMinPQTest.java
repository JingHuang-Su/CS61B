package bearmaps;

import org.junit.Test;
import edu.princeton.cs.algs4.Stopwatch;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public  void testRemoveSmallest(){
        ArrayHeapMinPQ<String> iLoveRank =  new ArrayHeapMinPQ<>();
        iLoveRank.add("iron_man", 7);
        iLoveRank.add("super_man", 5);
        iLoveRank.add("JingHuangSu_me", 1);
        iLoveRank.add("KingKong", 18);
        iLoveRank.add("Trump_man", 70);
        String Actakesmallest = iLoveRank.removeSmallest();
        String Expsmallest = "JingHuangSu_me";
        assertEquals(Expsmallest, Actakesmallest);

        iLoveRank.add("Ice_man", 3);
        iLoveRank.add("Spider_man", 23);
        iLoveRank.add("Super_girl", 13);
        String Actakesmallest2 = iLoveRank.removeSmallest();
        String Expsmallest2 = "Ice_man";
        assertEquals(Expsmallest2, Actakesmallest2);

    }

    @Test
    public void testTimeRemoveSmallest() {
        ArrayHeapMinPQ<String> ijusttest_AM = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_AM.add("I" + i, i + 1);
            ijusttest_AM.add("Love" + i, i + 1);
            ijusttest_AM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_AM.removeSmallest();
        }

        System.out.println("Total time elapsed for 1000_ArrayHeapMinPQ: "
                + sw1.elapsedTime() + " seconds.");

        NaiveMinPQ<String> ijusttest_NM = new NaiveMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_NM.add("I" + i, i + 1);
            ijusttest_NM.add("Love" + i, i + 1);
            ijusttest_NM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_NM.removeSmallest();
        }

        System.out.println("Total time elapsed for 1000_NaiveHeapMinPQ: "
                + sw2.elapsedTime() + " seconds.");
    }

    @Test
    public  void testGetSmallest(){
        ArrayHeapMinPQ<String> my_Favoriate = new ArrayHeapMinPQ<>();
        my_Favoriate.add("NBA", 171);
        my_Favoriate.add("CPBL", 13);
        my_Favoriate.add("MLB", 2);
        my_Favoriate.add("FootBall", 128);
        my_Favoriate.add("CBA", 1170);
        String Actakesmallest =  my_Favoriate.getSmallest();
        String Expsmallest = "MLB";
        assertEquals(Expsmallest, Actakesmallest);

        my_Favoriate.removeSmallest();
        my_Favoriate.add("NFL", 3);
        my_Favoriate.add("WNBA", 23);
        my_Favoriate.add("Idontknow", 13);
        String Actakesmallest2 = my_Favoriate.removeSmallest();
        String Expsmallest2 = "NFL";
        assertEquals(Expsmallest2, Actakesmallest2);

    }

    @Test
    public void testTimeGetSmallest() {
        ArrayHeapMinPQ<String> ijusttest_AM = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_AM.add("I" + i, i + 1);
            ijusttest_AM.add("Love" + i, i + 1);
            ijusttest_AM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_AM.getSmallest();
        }

        System.out.println("Total time elapsed for 1000_ArrayHeapMinPQ: "
                + sw1.elapsedTime() + " seconds.");

        NaiveMinPQ<String> ijusttest_NM = new NaiveMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_NM.add("I" + i, i + 1);
            ijusttest_NM.add("Love" + i, i + 1);
            ijusttest_NM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_NM.getSmallest();
        }

        System.out.println("Total time elapsed for 1000_NaiveHeapMinPQ: "
                + sw2.elapsedTime() + " seconds.");
    }

    @Test
    public void testChangePriority(){
        ArrayHeapMinPQ<String> my_Favoriate2 = new ArrayHeapMinPQ<>();
        my_Favoriate2.add("NBA", 171);
        my_Favoriate2.add("CPBL", 13);
        my_Favoriate2.add("MLB", 2);
        my_Favoriate2.add("FootBall", 128);
        my_Favoriate2.add("CBA", 1170);
        my_Favoriate2.changePriority("NBA", 1);
        String Actakesmallest =  my_Favoriate2.getSmallest();
        String Expsmallest = "NBA";
        assertEquals(Expsmallest, Actakesmallest);
    }

    @Test
    public void testTimeChangePriority() {
        ArrayHeapMinPQ<String> ijusttest_AM = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_AM.add("I" + i, i + 1);
            ijusttest_AM.add("Love" + i, i + 1);
            ijusttest_AM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw1 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_AM.changePriority("I" + i, 10000-1 );
        }

        System.out.println("Total time elapsed for 1000_ArrayHeapMinPQ: "
                + sw1.elapsedTime() + " seconds.");

        NaiveMinPQ<String> ijusttest_NM = new NaiveMinPQ<>();
        for(int i = 0; i < 10000; i++) {
            ijusttest_NM.add("I" + i, i + 1);
            ijusttest_NM.add("Love" + i, i + 1);
            ijusttest_NM.add("CS61B" + i, i + 1);
        }

        Stopwatch sw2 = new Stopwatch();
        for(int i = 0; i < 10000; i++){
            ijusttest_NM.changePriority("I" + i, 10000-1 );
        }

        System.out.println("Total time elapsed for 1000_NaiveHeapMinPQ: "
                + sw2.elapsedTime() + " seconds.");
    }

    @Test
    public void testSize(){
        ArrayHeapMinPQ<String> my_Favoriate =  new ArrayHeapMinPQ<>();
        my_Favoriate.add("NBA", 171);
        my_Favoriate.add("CPBL", 13);
        my_Favoriate.add("MLB", 2);
        my_Favoriate.add("FootBall", 128);
        my_Favoriate.add("CBA", 1170);
        int Actakesmallest =  my_Favoriate.size();
        int Expsmallest = 5;
        assertEquals(Expsmallest, Actakesmallest);

        my_Favoriate.removeSmallest();
        my_Favoriate.add("NFL", 3);
        my_Favoriate.add("WNBA", 23);
        my_Favoriate.add("Idontknow", 13);
        int Actakesmallest2 = my_Favoriate.size();
        int Expsmallest2 = 7;
        assertEquals(Expsmallest2, Actakesmallest2);
    }

    @Test
    public void testContain() {
        ArrayHeapMinPQ<String> my_Favoriate =  new ArrayHeapMinPQ<>();
        my_Favoriate.add("NBA", 171);
        my_Favoriate.add("CPBL", 13);
        my_Favoriate.add("MLB", 2);
        my_Favoriate.add("FootBall", 128);
        my_Favoriate.add("CBA", 1170);

        assertEquals(true,  my_Favoriate.contains("MLB"));

        my_Favoriate.removeSmallest();
        my_Favoriate.add("NFL", 3);
        my_Favoriate.add("WNBA", 23);
        my_Favoriate.add("Idontknow", 13);

        assertEquals(false,  my_Favoriate.contains("MLB"));
    }

    @Test
    public void testTimeTotal(){
        Stopwatch sw01 = new Stopwatch();
        ArrayHeapMinPQ<String> am1 = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 1000; i++){
            am1.add("CS61A" + i, i+1);
            am1.add("CS61B" + i, i+1);
            am1.getSmallest();
            am1.changePriority("CS61A" + i, 1000 - i);
            am1.contains("CS61B" + i);
            am1.size();
            am1.removeSmallest();
        }
        System.out.println("Total time elapsed for 1000_ArrayHeapMinPQ: "
                + sw01.elapsedTime() + " seconds.");

        Stopwatch sw02 = new Stopwatch();
        ArrayHeapMinPQ<String> am2 = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 10000; i++){
            am2.add("CS61A" + i, i+1);
            am2.add("CS61B" + i, i+1);
            am2.getSmallest();
            am2.changePriority("CS61A" + i, 1000 - i);
            am2.contains("CS61B" + i);
            am2.size();
            am2.removeSmallest();
        }
        System.out.println("Total time elapsed for 10000_ArrayHeapMinPQ: "
                + sw02.elapsedTime() + " seconds.");

        Stopwatch sw03 = new Stopwatch();
        ArrayHeapMinPQ<String> am3 = new ArrayHeapMinPQ<>();
        for(int i = 0; i < 40000; i++){
            am3.add("CS61A" + i, i+1);
            am3.add("CS61B" + i, i+1);
            am3.getSmallest();
            am3.changePriority("CS61A" + i, 1000 - i);
            am3.contains("CS61B" + i);
            am3.size();
            am3.removeSmallest();
        }
        System.out.println("Total time elapsed for 40000_ArrayHeapMinPQ: "
                + sw03.elapsedTime() + " seconds.");

        System.out.println("**************************************");


        Stopwatch sw1 = new Stopwatch();
        NaiveMinPQ<String> na1 = new NaiveMinPQ<>();
        for(int i = 0; i < 1000; i++){
            na1.add("hello" + i, i+1);
            na1.add("hi" + i, i+1);
            na1.getSmallest();
            na1.changePriority("hello" + i, 1000 - i);
            na1.contains("hello" + i);
            na1.size();
            na1.removeSmallest();
        }
        System.out.println("Total time elapsed for 1000_NaiveMinPQ: "
                + sw1.elapsedTime() + " seconds.");

        Stopwatch sw2 = new Stopwatch();
        NaiveMinPQ<String> na2 = new NaiveMinPQ<>();
        for(int i = 0; i < 10000; i++){
            na2.add("hello" + i, i+1);
            na2.add("hi" + i, i+1);
            na2.getSmallest();
            na2.changePriority("hello" + i, 10000 - i);
            na2.contains("hello" + i);
            na2.size();
            na2.removeSmallest();
        }
        System.out.println("Total time elapsed for 10000_NaiveMinPQ: "
                + sw2.elapsedTime() + " seconds.");

        Stopwatch sw3 = new Stopwatch();
        NaiveMinPQ<String> na3 = new NaiveMinPQ<>();
        for(int i = 0; i < 40000; i++){
            na3.add("hello" + i, i+1);
            na3.add("hi" + i, i+1);
            na3.getSmallest();
            na3.changePriority("hello" + i, 40000 - i);
            na3.contains("hello" + i);
            na3.size();
            na3.removeSmallest();
        }
        System.out.println("Total time elapsed for 40000_NaiveMinPQ: "
                + sw3.elapsedTime() + " seconds.");
    }
}
