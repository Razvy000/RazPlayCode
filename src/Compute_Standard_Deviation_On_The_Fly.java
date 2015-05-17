
import java.util.ArrayList;
import java.util.List;

public class Compute_Standard_Deviation_On_The_Fly {

    public double standardDeviation(List<Double> list) {

        double mean = 0;
        double std = 0;
        for (Double elem : list) {
            mean += elem;
        }
        mean /= list.size();

        for (Double elem : list) {
            double diff = elem - mean;
            std += diff * diff;
        }
        std /= list.size() - 1;
        std = Math.sqrt(std);
        return std;
    }

    // Welford's method
    public double standardDeviationFly(List<Double> list) {
        double M = 0.0;
        double S = 0.0;
        int k = 1;

        for (Double value : list) {
            double tmpM = M;
            M += (value - tmpM) / k;
            S += (value - tmpM) * (value - M);
            k++;
        }
        return Math.sqrt(S / (k - 2)); // if population S/k-1
    }
    
    // expand the equaltion
    public double standardDeviation2(List<Double> list) {
        
        // sum(x-mean)^2 = 
        // sumx^2 - mean^2
        double mean = 0;
        double std = 0;
        for (Double elem : list) {
            mean += elem;
        }
        mean /= list.size();

        for (Double elem : list) {
            std += elem * elem;
        }
        std -= mean*mean;
        std /= list.size()-1;
        std = Math.sqrt(std);
        return std;
    }

    public static void main(String[] args) {
        Compute_Standard_Deviation_On_The_Fly o = new Compute_Standard_Deviation_On_The_Fly();
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 20};
        List<Double> b = new ArrayList<>();
        for (int i = 0; i < a.length; i++)
            b.add(new Double(a[i]));

        double r = o.standardDeviation(b);
        double r2 = o.standardDeviationFly(b);
        double r3 = o.standardDeviationFly(b);
        System.out.println(r);
        System.out.println(r2);
        System.out.println(r3);
    }
}
