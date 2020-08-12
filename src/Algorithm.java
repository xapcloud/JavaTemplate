package src;

public class Algorithm {

    int Fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }

    int Fibonacci1(int n) {
        if (n <= 1) {
            return n;
        } else {
            int a = 0, b = 1;
            for (int i = 2; i <= n; i++) {
                b = a + b;
                a = b - a;
            }
            return b;
        }
    }

    int Fibonacci2(int n) {
        double sqrt5 = Math.sqrt(5);
        return (int) (sqrt5 / 5 * (Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n)));
    }

    int Fibonacci3(int n) {
        if (n <= 1) {
            return n;
        } else {
            return Fibonacci3Helper(n)[0][1];
        }
    }

    int[][] Fibonacci3Helper(int n) {
        if (n <= 1) {
            return new int[][]{{1, 1}, {1, 0}};
        } else if ((n & 1) == 0) {
            int[][] temp = Fibonacci3Helper(n / 2);
            temp = matrixMultiply(temp, temp);
            return temp;
        } else {
            int[][] temp = Fibonacci3Helper(n / 2);
            temp = matrixMultiply(temp, temp);
            temp = matrixMultiply(temp, new int[][]{{1, 1}, {1, 0}});
            return temp;
        }
    }

    public static int[][] matrixMultiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        int x, i, j;
        for (i = 0; i < a.length; i++) {
            for (j = 0; j < b[0].length; j++) {
                int temp = 0;
                for (x = 0; x < b.length; x++) {
                    temp += a[i][x] * b[x][j];
                }
                c[i][j] = temp;
            }
        }
        return c;
    }
}
