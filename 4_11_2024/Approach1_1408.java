public class Approach1_1408 {
    public static void main(String[] args){
        String[] words = {"Hello", "He", "Hero", "SuperHero"};
        String[] res = new String[words.length];

        matching_words(words, res);
    }
    public static void matching_words(String[] words, String[] res){
        for(int i = 0 ; i < words.length ; i++){
            for(int j = 0 ; j < words.length ; j++){
                if(i != j){
                    if(words[j].contains(words[i])){
                        res[i] = words[i];
                    }
                }
            }
        }
        System.out.println("The matching words in the above are " );
        for(int i = 0 ; i < res.length ; i++){
            if(res[i] != null) System.out.println(res[i]);
        }
    }
}
