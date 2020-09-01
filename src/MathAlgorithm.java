package src;

public class MathAlgorithm {

    int module = (int) 1e9 + 7;

    int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }

    // Within time complexity O(n)
    int Fibonacci1(int n) {
        if (n <= 1) {
            return n;
        } else {
            int a = 0, b = 1;
            for (int i = 2; i <= n; i++) {
                b = a + b;
                a = b - a;
                b %= module;
                a %= module;
            }
            return b;
        }
    }

    // Good efficiency but low accuracy, make mistake at n = 45
    // Within time complexity O(log(n)) (if fast power for pow)
    int Fibonacci2(int n) {
        double sqrt5 = java.lang.Math.sqrt(5);
        return (int) (sqrt5 / 5 * (java.lang.Math.pow((1 + sqrt5) / 2, n) - java.lang.Math.pow((1 - sqrt5) / 2, n)));
    }

    // Best method when n is large
    int Fibonacci3(int n) {
        if (n <= 1) {
            return n;
        } else {
            long[][] m = {{1, 1}, {1, 0}};
            long[][] init = {{1}, {0}};
            return (int) matrixMultiply(fastPowerMatrix(m, n - 1), init)[0][0];
        }
    }

    // O(m * n * k)
    long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        int i, j, k;
        for (i = 0; i < a.length; i++) {
            for (j = 0; j < b[0].length; j++) {
                long temp = 0;
                for (k = 0; k < b.length; k++) {
                    temp += (a[i][k] * b[k][j]) % module;
                }
                c[i][j] = temp % module;
            }
        }
        return c;
    }

    int fastPower(int a, int b) {
        long ans = 1;
        long base = a % module;
        while (b != 0) {
            if ((b & 1) != 0) {
                ans = (ans * base) % module;
            }
            base = (base * base) % module;
            b >>= 1;
        }
        return (int) ans;
    }

    // 矩阵乘法满足结合律， 仅限方阵
    long[][] fastPowerMatrix(long[][] m, int n) {
        int len = m.length;
        long[][] ans = new long[len][len];
        for (int i = 0; i < len; i++) {
            ans[i][i] = 1;
        }
        long[][] base = m;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans = matrixMultiply(ans, base);
            }
            base = matrixMultiply(base, base);
            n >>= 1;
        }
        return ans;
    }

    long combination(int n, int k) {
        long[][] matrix = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (j == 0) {
                    matrix[i][j] = 1;
                } else if (j == 1) {
                    matrix[i][j] = i;
                } else {
                    matrix[i][j] = (matrix[i - 1][j - 1] + matrix[i - 1][j]) % module;
                }
            }
        }
        return matrix[n][k];
    }
}
