/**1. Give	an algorithm for reversing words in a sentence.
Input: “This is	a Career Monk String”,	Output:	“String	Monk Career a is This” */
public class Q1 {
    public static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder reversed = new StringBuilder();
        
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i != 0) {
                reversed.append(" "); // Add space between words
            }
        }
        
        return reversed.toString();
    }

    public static void main(String[] args) {
        String input = "This is a Career Monk String";
        String output = reverseWords(input);
        System.out.println(output); // "String Monk Career a is This"
    }
}
