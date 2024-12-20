public class Non_repeatingchar {
    public static void main(String[] args){
        String str = "aabccc";
        char ch = findNonRepeatingChar(str);
        System.out.println("Non-repeating character in "+str+ "is " +ch);
    }
    public static char findNonRepeatingChar(String str){
        int[] freq = new int[256];
        for(char ch : str.toCharArray()){
            freq[ch]++;
        }
        for(char ch : str.toCharArray()){
            if(freq[ch]==1){
                return ch;
            }
        }
        return ' ';
    }
}
