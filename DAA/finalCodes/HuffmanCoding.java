package atharva.com.ArrayList;

import java.util.*;

class Node {
    int freq;
    char symbol;
    Node left;
    Node right;
    boolean isLeaf;

    // Constructor for leaf nodes
    Node(char symbol, int freq) {
        this.symbol = symbol;
        this.freq = freq;
        this.isLeaf = true;
    }

    // Constructor for internal nodes
    Node(int freq, Node left, Node right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
        this.isLeaf = false;
    }
}
public class HuffmanCoding {
    // Method to generate Huffman codes and store them in a map
    public static void generateHuffmanCodes(Node node, String code, Map<Character, String> codeMap) {
        if (node.isLeaf) {
            codeMap.put(node.symbol, code);
        } else {
            generateHuffmanCodes(node.left, code + "0", codeMap);
            generateHuffmanCodes(node.right, code + "1", codeMap);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string:");
        String text = scanner.nextLine();
        scanner.close();

//        if (text.isEmpty()) {
//            System.out.println("The input string is empty. Exiting.");
//            return;
//        }

        // Calculate frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        System.out.println("Frequency: " + freqMap);

        // Priority queue to build the Huffman Tree
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.freq));

        // Create leaf nodes for each character and add to the priority queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman Tree
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node newNode = new Node(left.freq + right.freq, left, right);
            priorityQueue.add(newNode);
        }

        // The root node of the Huffman Tree
        Node root = priorityQueue.poll();

        // Generate Huffman codes
        Map<Character, String> codeMap = new HashMap<>();
        generateHuffmanCodes(root, "", codeMap);

        System.out.println("Huffman Codes: " + codeMap);

        // Encode the input text
        StringBuilder encodedString = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedString.append(codeMap.get(c));
        }

        System.out.println("Encoded String: " + encodedString.toString());
    }
}
