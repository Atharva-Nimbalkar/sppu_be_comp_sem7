package atharva.com.ArrayList;
import java.util.*;

public class Fibonacci{

    // Variables to count steps in iterative and recursive approaches
    public static int iSteps = 0;
    public static int rSteps = 0;

    // Iterative approach to calculate the nth Fibonacci sequence up to n
    public static List<Integer> iStepFibonacciSequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        if (n >= 0) sequence.add(0);
        if (n >= 1) sequence.add(1);

        int a = 0, b = 1;
        iSteps = 2;  // Start counting from the base cases

        for (int i = 2; i <= n; i++) {
            iSteps++;
            int ans = a + b;
            sequence.add(ans);
            a = b;
            b = ans;
        }
        return sequence;
    }

    // Recursive approach to calculate the nth Fibonacci number with memoization
    public static int rStepFibonacci(int n) {
        rSteps++;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return rStepFibonacci(n - 1) + rStepFibonacci(n - 2);
    }

    // Helper function to generate the entire Fibonacci sequence recursively
    public static List<Integer> recursiveFibonacciSequence(int n) {
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            sequence.add(rStepFibonacci(i));
        }
        return sequence;
    }

    // Main method to handle user choice and display time/memory usage
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the approach:");
        System.out.println("1: Non-recursive (Iterative)");
        System.out.println("2: Recursive");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        System.out.print("Enter nth Fibonacci term: ");
        int n = scanner.nextInt();

        long startTime = System.nanoTime();
        List<Integer> sequence;
        long endTime;

        if (choice == 1) {
            // Iterative approach
            System.out.println("Iterative approach:");
            sequence = iStepFibonacciSequence(n);
            endTime = System.nanoTime();

            System.out.println(sequence);
            System.out.println("Steps required using recursion: " + iSteps);
            System.out.printf("Execution time: %.6f seconds\n", (endTime - startTime) / 1_000_000_000.0);
            System.out.printf("Peak memory usage: %.2f KB\n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024.0);
        } else if (choice == 2) {
            // Recursive approach
            System.out.println("Recursive approach:");
            rSteps = 0;  // Reset step counter for recursion
            startTime = System.nanoTime();

            sequence = recursiveFibonacciSequence(n);
            endTime = System.nanoTime();

            System.out.println(sequence);
            System.out.println("Steps required using recursion: " + rSteps);
            System.out.printf("Execution time: %.6f seconds\n", (endTime - startTime) / 1_000_000_000.0);
            System.out.printf("Peak memory usage: %.2f KB\n", (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024.0);
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
}
