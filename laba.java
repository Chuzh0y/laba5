import java.io.*;
import java.util.*;


class ProductInventory {
    public static void main(String[] args) {
        // First task - find longest word and its frequency
        try {
            findLongestWord("input.txt");
        } catch (IOException e) {
            System.out.println("Error reading file for task 1: " + e.getMessage());
        }

        // Second task - product inventory
        try {
            processInventory("products.txt");
        } catch (IOException e) {
            System.out.println("Error reading inventory: " + e.getMessage());
        }
    }

    private static void findLongestWord(String filename) throws IOException {
        String longestWord = "";
        Map<String, Integer> wordFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
            }
        }

        System.out.println("Longest word: " + longestWord);
        System.out.println("It appears " + wordFrequency.get(longestWord) + " times");
    }

    private static void processInventory(String filename) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchProduct = scanner.nextLine();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String unit = parts[1].trim();
                    String type = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());

                    if (name.equalsIgnoreCase(searchProduct)) {
                        double totalAmount = quantity * price;
                        System.out.println("\nProduct Information:");
                        System.out.println("Name: " + name);
                        System.out.println("Unit: " + unit);
                        System.out.println("Type: " + type);
                        System.out.println("Quantity: " + quantity);
                        System.out.println("Unit Price: $" + String.format("%.2f", price));
                        System.out.println("Total Amount: $" + String.format("%.2f", totalAmount));
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("Product not found in inventory.");
        }
        scanner.close();
    }
}

