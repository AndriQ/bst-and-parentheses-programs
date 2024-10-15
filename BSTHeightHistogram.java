import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BSTHeightHistogram {

    // Node class defining BST node
    static class Node {
        int number;
        Node left;
        Node right;
  
        Node(int number) {
            this.number = number;
            left = null;
            right = null;
        }
    }

    // Construct BST from given permutation
    public static Node constructBST(List<Integer> permutation) {
        Node root = null;
        for (int number : permutation) {
            root = insert(root, number);
        }
        return root;
    }

    // Insert node into BST
    public static Node insert(Node root, int number) {
        if (root == null) {
            return new Node(number);
        }
        if (number < root.number) {
            root.left = insert(root.left, number);
        } else if (number > root.number) {
            root.right = insert(root.right, number);
        }
        return root;
    }

    // Getting height of BST
    public static int getHeight(Node root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Generate permutations of the list and BST
    public static void permute(List<Integer> permutation, int index, List<Integer> heights) {
        if (index == permutation.size() - 1) {
            Node root = constructBST(permutation);
            int height = getHeight(root);
            heights.add(height);
        } else {
            for (int i = index; i < permutation.size(); i++) {
                Collections.swap(permutation, i, index);
                permute(permutation, index + 1, heights);
                Collections.swap(permutation, i, index);
            }
        }
    }

    // Printing histogram with frequencies and average height
    public static void printHistogram(List<Integer> heights, int n) {
        int[] frequency = new int[n + 1];
        for (int height : heights) {
            frequency[height]++;
        }

        System.out.println("Height   Frequency");
        System.out.println("-----------------");
        double sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(i + "        " + frequency[i]);
            sum += i * frequency[i];
        }
        System.out.println("\nAverage height of BSTs:");
        System.out.println(sum / factorial(n));
    }

    // Computing factorial for average height
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // Initiate the computations and print the histogram
    public static void compute(int n) {
        List<Integer> heights = new ArrayList<>();

        List<Integer> permutation = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            permutation.add(i);
        }
        permute(permutation, 0, heights);
        printHistogram(heights, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a positive integer n:");
        int n = scanner.nextInt();
        scanner.close();

        compute(n);
    }
}
