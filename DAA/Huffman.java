import java.util.*;

class Node {
    boolean isLeaf;
    int freqSum;
    char c;
    Node left;
    Node right;

    // Constructor for leaf nodes
    public Node(char c, int f) {
        this.c = c;
        this.freqSum = f;
        this.left = null;
        this.right = null;
        this.isLeaf = true;
    }

    // Constructor for internal nodes
    public Node(int f, Node l, Node r) {
        this.isLeaf = false;
        this.freqSum = f;
        this.left = l;
        this.right = r;
    }
}

public class Huffman {
    // Method to build the code table using the Huffman Tree
    public static void buildCodeTable(Node root, String code, Map<Character, String> codeTable) {
        if (root == null) return;

        if (root.isLeaf) {
            codeTable.put(root.c, code);
            return;
        }

        buildCodeTable(root.left, code + '0', codeTable);
        buildCodeTable(root.right, code + '1', codeTable);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string to encode: ");
        String s = scanner.nextLine();

        // Build frequency table
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        System.out.println("Frequency of characters:");
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // Build initial nodes list
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Sort nodes by frequency sum in ascending order
        nodes.sort(Comparator.comparingInt(a -> a.freqSum));

        // Build the Huffman Tree
        while (nodes.size() > 1) {
            int sum = nodes.get(0).freqSum + nodes.get(1).freqSum;
            Node parent = new Node(sum, nodes.get(0), nodes.get(1));

            // Remove the two nodes with smallest frequencies
            nodes.remove(0);
            nodes.remove(0);

            nodes.add(parent);

            // Sort nodes again after inserting the new parent node
            nodes.sort(Comparator.comparingInt(a -> a.freqSum));
        }

        // Root of the Huffman Tree
        Node root = nodes.get(0);

        // Build the code table
        Map<Character, String> codes = new HashMap<>();
        buildCodeTable(root, "", codes);

        // Print the code table
        System.out.println("Code Table:");
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // Encode the string
        StringBuilder encodedStr = new StringBuilder();
        for (char c : s.toCharArray()) {
            encodedStr.append(codes.get(c));
        }

        System.out.println("Encoded String:");
        System.out.println(encodedStr.toString());

        scanner.close();
    }
}

//Overall TC  O(n+dlogd)
//Overall SC O(n+d)