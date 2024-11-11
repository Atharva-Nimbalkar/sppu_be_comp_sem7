import java.util.*;

public class Nqueen2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        int[][] board = new int[n][n];
        nQueen(board, 0, n);

        System.out.println("--------All possible solutions--------");
        scanner.close();
    }

    public static void nQueen(int[][] board, int x, int n) {
        if (x == n) {
            printBoard(board, n);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, x, col, n)) {
                board[x][col] = 1;
                nQueen(board, x + 1, n);
                board[x][col] = 0;
            }
        }
    }

    public static boolean isSafe(int[][] board, int x, int y, int n) {
        // Check for same column
        for (int row = 0; row < x; row++) {
            if (board[row][y] == 1) {
                return false;
            }
        }

        // Check upper left diagonal
        int row = x, col = y;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == 1) {
                return false;
            }
            row--;
            col--;
        }

        // Check upper right diagonal
        row = x;
        col = y;
        while (row >= 0 && col < n) {
            if (board[row][col] == 1) {
                return false;
            }
            row--;
            col++;
        }

        return true;
    }

    public static void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) System.out.print("[Q]");
                else System.out.print("[]");
            }
            System.out.println();
        }
        System.out.println();
    }


}

//TC o(N!)
//SC o(N^2)