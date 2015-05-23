
public class Greatest_Common_Divisor {

    // Euclid's method
    int gcd(int a, int b) {
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    int gcd2(int a, int b) {
        while (a != b) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        return a;
    }

    public static void main(String[] args) {
        Greatest_Common_Divisor g = new Greatest_Common_Divisor();
        System.out.println(g.gcd(14, 15));
        System.out.println(g.gcd2(10, 20));
    }
}
