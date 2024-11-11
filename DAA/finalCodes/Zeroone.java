import java.util.*;

public class Zeroone {
    public static int knapsack(int[] wt, int[] val, int n, int W) {
        int dp[][] = new int[n][W + 1];

        // Base Condition
        for (int i = wt[0]; i <= W; i++) {
            dp[0][i] = val[0];
        }
        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= W; cap++) {
                // Calculate the maximum value when the current item is not taken
                int notTaken = dp[ind - 1][cap];

                // Calculate the maximum value when the current item is taken
                int taken = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    taken = val[ind] + dp[ind - 1][cap - wt[ind]];
                }

                // Store the maximum value for the current state
                dp[ind][cap] = Math.max(notTaken, taken);
            }
        }

        // Backtrack to find the selected items
        int[] selected = new int[n];
        int cap = W;
        for (int i = n - 1; i > 0; i--) {
            if (dp[i][cap] != dp[i - 1][cap]) {
                selected[i] = 1;
                cap -= wt[i];
            }
        }
        if (dp[0][cap] != 0) selected[0] = 1;

        // Print selected items
        System.out.println("Selected items (1 = included, 0 = not included):");
        for (int i = 0; i < n; i++) {
            System.out.println("Item " + (i + 1) + ": " + selected[i]);
        }

        // The result is stored in the last row and last column of the DP array
        return dp[n - 1][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take input for number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        // Initialize weight and value arrays
        int[] wt = new int[n];
        int[] val = new int[n];

        // Take input for profits/values as space-separated
        System.out.print("Enter the profits/values of items separated by spaces (e.g., '60 100 120'): ");
        for (int i = 0; i < n; i++) {
            val[i] = scanner.nextInt();
        }

        // Take input for weights as space-separated
        System.out.print("Enter the weights of items separated by spaces (e.g., '10 20 30'): ");
        for (int i = 0; i < n; i++) {
            wt[i] = scanner.nextInt();
        }

        // Take input for knapsack capacity
        System.out.print("Enter the capacity of the knapsack: ");
        int W = scanner.nextInt();

        // Calculate and print the maximum value of items the thief can steal
        System.out.println("Maximum profit in knapsack = " + knapsack(wt, val, n, W));

        scanner.close();
    }
}
