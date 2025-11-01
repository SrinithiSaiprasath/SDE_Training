public class q11{
    public static void main(String[] args){
        int s = 5 ; 
        System.out.println(" Number of Handshakes " + numHandShakes(s));
    }

    private static int numHandShakes(int num){
        return  num*(num-1) / 2; 
    }
}