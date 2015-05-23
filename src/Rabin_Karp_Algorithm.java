
import java.util.HashSet;
import java.util.Set;

public class Rabin_Karp_Algorithm {

    int indexOf(String S, String W) {
        if (W.length() > S.length())
            return -1;
        return indexOf(S.toCharArray(), W.toCharArray());
    }

    int indexOf(char[] S, char[] W) {
        int n = S.length;
        int m = W.length;
        int hw = rollingHash(W, 0, m - 1);
        int hs = rollingHash(S, 0, m - 1);

        for (int i = 0; i < n - m + 1; i++) {
            if (hs == hw) {
                boolean equals = true;
                for (int j = 0; j < m; j++)
                    if (S[i + j] != W[j]) {
                        equals = false;
                        break;
                    }
                if (equals)
                    return i;
            }

            hs = rollingHash(S, i + 1, i + m);
        }
        return -1;
    }

    // plagiarism detection
    int indexOfMany(String Str, Set<String> subs) {
        char[] S = Str.toCharArray();
        int m = subs.iterator().next().length();
        int n = S.length;

        Set<Integer> hsubs = new HashSet<>();

        for (String sub : subs) {
            hsubs.add(rollingHash(sub.toCharArray(), 0, m - 1));
        }

        // takes O(n) for k subs, instead of O(n*k)
        int hs = rollingHash(S, 0, m - 1);
        for (int i = 0; i < n - m + 1; i++) {
            if (hsubs.contains(hs))
                if (subs.contains(new String(S, i, m)))
                    return i;
            hs = rollingHash(S, i + 1, i + m);
        }
        return -1;
    }

    int lastHash;

    int rollingHash(char[] W, int a, int b) {
        int h = 0;
        if (a == 0) {
            // takes O(m)           
            h = W[a];
            for (int i = a + 1; i <= b; i++) {
                h *= 101;
                h += W[i];
            }
        } else {
            // takes O(1)
            int m = b - a;
            int oldElem = W[a - 1];
            int newElem = W[b];
            h = 101 * (lastHash - oldElem * (int) (Math.pow(101, m))) + newElem;
        }
        lastHash = h;
        return h;
    }

    public static void main(String[] args) {
        Rabin_Karp_Algorithm rk = new Rabin_Karp_Algorithm();
        System.out.println(rk.indexOf("razvanvreamererazvn", "razvn"));

        Set<String> subs = new HashSet<>();
        subs.add("am");
        //subs.add("re");
        subs.add("vn");

        System.out.println(rk.indexOfMany("razvanvreamererazvn", subs));
    }
}
