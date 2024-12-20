
// replace all 0 ot 1
public class q4 {
    public static void main(String[] args){
        int a = 120 ; 
        String s = Integer.toBinaryString(a) ; 
        System.out.println(s);
        String r =s.replace("1" , "@") ;
        String t = r.replace("0", "1") ;
        String u = t.replace("@" , "0") ;
        System.out.println(u);

    }
}
