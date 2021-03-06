// This is a mutant program.
// Author : ysma

import java.util.Arrays;
import java.util.Stack;


public class superstring
{

    public  java.lang.String shortestSuperstring( java.lang.String[] A )
    {
        int N = A.length;
        int[][] overlaps = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i != j) {
                    int m = Math.min( A[i].length(), A[j].length() );
                    for (int k = m; k >= 0; --k) {
                        if (A[i].endsWith( A[j].substring( 0, k ) )) {
                            overlaps[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        int[][] dp = new int[1 << N][N];
        int[][] parent = new int[1 << N][N];
        for (int mask = 0; mask < 1 << N; ++mask) {
            Arrays.fill( parent[mask], -1 );
        }
        int[] perm = new int[N];
        boolean[] seen = new boolean[N];
        int t = 0;
        int mask = (1 << N) - 1;
        int p = 0;
        for (int j = 0; j < N; ++j) {
            if (dp[(1 << N) - 1][j] > dp[(1 << N) - 1][p]) {
                p = j;
            }
        }
        while (p != -1) {
            perm[t++] = p;
            seen[p] = true;
            int p2 = parent[mask][p];
            mask ^= 1 << p;
            p = p2;
        }
        for (int i = 0; i < t / 2; ++i) {
            int v = perm[i];
            perm[i] = perm[t - 1 - i];
            perm[t - 1 - i] = v;
        }
        for (int i = 0; i < N; ++i) {
            if (!seen[i]) {
                perm[t++] = i;
            }
        }
        java.lang.StringBuilder ans = new java.lang.StringBuilder( A[perm[0]] );
        for (int i = 1; i < N; ++i) {
            int overlap = overlaps[perm[i - 1]][perm[i]];
            ans.append( A[perm[i]].substring( overlap ) );
        }
        return ans.toString();
    }

}
