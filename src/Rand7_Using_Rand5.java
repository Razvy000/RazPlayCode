
import java.util.Arrays;
import java.util.Random;

public class Rand7_Using_Rand5 {

    Random rand = new Random();

    // generate 0,1,2,3,4

    public int rand5() {
        //return (int)(Math.random()*6);
        return rand.nextInt(5);
    }

    // generate 0,1,2,3,4,5,6
    public int rand7() {

        while (true) {
            // 0 ... 24
            int r = 5 * rand5() + rand5();

            // 0-6, 7-13, 13-20
            if (r < 21)
                return r % 7;
        }
    }

    public static void main(String[] args) {
        Rand7_Using_Rand5 o = new Rand7_Using_Rand5();
        int[] count = new int[7];
        for (int i = 0; i < 700000; i++) {
            count[o.rand7()]++;
        }

        System.out.println(Arrays.toString(count));

    }
}
