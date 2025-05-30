import java.util.*;

public class MessageParsing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of input pairs
        int n = Integer.parseInt(scanner.nextLine());
        
        // Store characters at their respective indices
        Map<Long, Character> indexCharMap = new TreeMap<>();
        
        // Process each input pair
        for (int i = 0; i < n; i++) {
            String[] pair = scanner.nextLine().split(" ");
            long index = Long.parseLong(pair[0]);
            char character = pair[1].charAt(0);
            
            // Store the character at its index
            indexCharMap.put(index, character);
            
            // Check for valid messages after adding each character
            checkAndPrintValidMessages(indexCharMap);
        }
        
        scanner.close();
    }
    
    private static void checkAndPrintValidMessages(Map<Long, Character> indexCharMap) {
        // List to store valid messages with their starting indices for sorting
        List<Pair<Long, String>> validMessages = new ArrayList<>();
        
        // Check all possible valid message ranges
        for (Long l : indexCharMap.keySet()) {
            for (Long r : indexCharMap.keySet()) {
                // A valid message must have l < r
                if (l >= r) continue;
                
                // Check if we have enclosing '*' characters at positions l-1 and r+1
                if (!indexCharMap.containsKey(l-1) || indexCharMap.get(l-1) != '*' ||
                    !indexCharMap.containsKey(r+1) || indexCharMap.get(r+1) != '*') {
                    continue;
                }
                
                // Extract the message between l and r (inclusive)
                StringBuilder message = new StringBuilder();
                boolean isValid = true;
                
                // Ensure all positions from l to r have valid characters
                for (long pos = l; pos <= r; pos++) {
                    if (!indexCharMap.containsKey(pos)) {
                        isValid = false;
                        break;
                    }
                    
                    char ch = indexCharMap.get(pos);
                    if (!Character.isLowerCase(ch) && ch != '*') {
                        isValid = false;
                        break;
                    }
                    
                    // Add the character to our message
                    message.append(indexCharMap.get(pos));
                }
                
                // If we have a valid non-empty message, add it to our list
                if (isValid && message.length() > 0) {
                    validMessages.add(new Pair<>(l, message.toString()));
                }
            }
        }
        
        // Sort valid messages by their starting index
        Collections.sort(validMessages, Comparator.comparing(Pair::getKey));
        
        // Print the valid messages
        for (Pair<Long, String> pair : validMessages) {
            System.out.println(pair.getValue());
        }
    }
    
    // Simple Pair class to store a key-value pair
    static class Pair<K, V> {
        private final K key;
        private final V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
}