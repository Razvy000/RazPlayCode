
public class Knuth_Morris_Pratt_Algorithm {

    // https://www.topcoder.com/community/data-science/data-science-tutorials/introduction-to-string-searching-algorithms/
    // http://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm
    
    // T is the ammount of backtracking, continue from m+i-T[i] 
    // after failed match when comparing S[m + i] to W[i]    
    int[] T;    

    void table(char[] S, char[] W) {
        int pos = 2;    // the current position we are computing in T
        int cnd = 0;    // the zero-based index in W of the next character of the current candidate substring

        T[0] = -1;  // we cannot backtrack for W[0] missmatch
        T[1] = 0;

        while (pos < W.length) {

            // first case: the substring continues
            if (W[pos - 1] == W[cnd]) {
                cnd++;
                T[pos] = cnd;
                pos++;
            } // sencond case: it doesn't but we can fall back
            else if (cnd > 0) {
                cnd = T[cnd];
            } // third case: we have run out of candidates, cnd=0
            else {
                T[pos] = 0;
                pos++;
            }
        }
    }
    /*
Therefore we compile the following table:
i	0	1	2	3	4	5	6
W[i]	A	B	C	D	A	B	D
T[i]	-1	0	0	0	0	1	2
    
Another example, more interesting and complex:
i	00	01	02	03	04	05	06	07	08	09	10	11	12	13	14	15	16	17	18	19	20	21	22	23
W[i]	P	A	R	T	I	C	I	P	A	T	E		I	N		P	A	R	A	C	H	U	T	E
T[i]	-1	0	0	0	0	0	0	0	1	2	0	0	0	0	0	0	1	2	3	0	0	0	0	0

    */

    int indexOf(char[] S, char[] W) {
        int m = 0;  // the beginning of the current match in S
        int i = 0;  // the position of the current character in W
        T = new int[W.length];

        // fill table
        table(S, W);

        while (m + i < S.length) {

            if (W[i] == S[m + i]) {
                if (i == W.length - 1)
                    return m;
                i++;
            } else {
                if (T[i] > -1) {
                    m = m + i - T[i];
                    i = T[i];
                } else {
                    i = 0;
                    m++;
                }
            }
        }
        return -1;
    }

    int indexOf(String S, String W) {
        return indexOf(S.toCharArray(), W.toCharArray());
    }

    public static void main(String[] args) {
        Knuth_Morris_Pratt_Algorithm kmp = new Knuth_Morris_Pratt_Algorithm();
        System.out.println(kmp.indexOf("razvanvreamererazvn", "razvn"));
    }
}
