
import java.util.ArrayList;
import java.util.List;

public class Compute_Mean_On_The_Fly {

    public double mean(List<Double> list) {

        double s = 0;
        for (Double elem : list) {
            s += elem;
        }

        return s / list.size();
    }

    public double meanFly(List<Double> list) {

        int n = 0;
        double ans = 0;
        for (Double elem : list) {
            ans = (ans * n + elem) / (n + 1);
            n = n + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Compute_Mean_On_The_Fly o = new Compute_Mean_On_The_Fly();
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 20};
        List<Double> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            b.add(new Double(a[i]));
        double r = o.mean(b);
        double r2 = o.meanFly(b);
        System.out.println(r);
        System.out.println(r2);
    }
}
