import java.util.*;

class Weight {
    int weight;
    int profit;
    double pwRatio;

    Weight(int w, int p) {
        this.weight = w;
        this.profit = p;
        this.pwRatio = (double) p / (double) w;
    }
}

public class Factionalknapsack {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number of Weights: ");
        int n = sc.nextInt();

        ArrayList<Weight> weights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Weight " + (i + 1) + ": ");
            int w = sc.nextInt();
            System.out.print("Enter Profit " + (i + 1) + ": ");
            int p = sc.nextInt();
            weights.add(new Weight(w, p));
        }
        System.out.println();

        // Sort by profit-to-weight ratio in descending order
        weights.sort(Comparator.comparingDouble((Weight w) -> w.pwRatio).reversed());

        print(weights);

        System.out.print("Enter Capacity: ");
        int capacity = sc.nextInt();

        System.out.println();
        System.out.println("Maximum Profit: " + fKnapsack(weights, capacity));

        sc.close();
    }

    public static void print(ArrayList<Weight> weights) {
        System.out.println("-----------");
        System.out.println(" W  P  P/W");
        for (Weight w : weights) {
            System.out.println(w.weight + " " + w.profit + " " + w.pwRatio);
        }
        System.out.println("-----------");
    }

    public static int fKnapsack(ArrayList<Weight> arr, int capacity) {
        int remainingCapacity = capacity;
        int totalProfit = 0;

        for (Weight w : arr) {
            System.out.println("Selected -> Weight: " + w.weight + ", Profit: " + w.profit + ", P/W Ratio: " + w.pwRatio);
            if (w.weight <= remainingCapacity) {
                remainingCapacity -= w.weight;
                totalProfit += w.profit;
            } else {
                // Fractional part
                int partialProfit = (int) (w.pwRatio * remainingCapacity);
                totalProfit += partialProfit;
                remainingCapacity = 0;
                return totalProfit;
            }
            System.out.println("Capacity Remaining: " + remainingCapacity + ", Profit: " + totalProfit);
        }
        return totalProfit;
    }


}
//TC O(nlogn)
//SC o(n)