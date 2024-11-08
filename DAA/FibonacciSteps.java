package atharva.com.ArrayList;
import java.util.*;

public class FibonacciSteps {

    // Variables to count steps in iterative and recursive approaches
    public static int iSteps = 0;
    public static int rSteps = 0;

    // Iterative approach to calculate the nth Fibonacci number
    public static int iStepFibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int a = 0, b = 1, ans = 0;
        iSteps = 2;  // Start counting from the base cases

        for (int i = 2; i <= n; i++) {
            iSteps++;
            ans = a + b;
            a = b;
            b = ans;
        }
        return ans;
    }

    // Recursive approach to calculate the nth Fibonacci number
    public static int rStepFibonacci(int n) {
        rSteps++;
        if (n == 0) return 0;
        if (n == 1) return 1;
        return rStepFibonacci(n - 1) + rStepFibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = scanner.nextInt();

        // Reset step counters for a fresh calculation
        iSteps = 0;
        rSteps = 0;

        // Calculate Fibonacci using recursion
        System.out.println("Fibonacci Value (Recursive): " + rStepFibonacci(n));
        System.out.println("Steps required using recursion: " + rSteps);

        // Calculate Fibonacci using iteration
        System.out.println("Fibonacci Value (Iterative): " + iStepFibonacci(n));
        System.out.println("Steps required using iteration: " + iSteps);

        scanner.close();
    }
}
//recursive
// tc O(2^n)
//s.c O(n)

//iteration
//tc O(n)
//sc O(1)