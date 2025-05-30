public class sam {
    
}

abstract class Sample{
    int add(int a , int b){
        return a+b ; 
    } 
    int sub(int a , int b) {
        return a-b ;
    }
}

class Sam2 extends Sample{

    public int add(int a , int b){
        return a+b ; 
    }

    // @Override
    public int sub(int a, int b) {
        return a-b ; 
        // throw new UnsupportedOperationException("Unimplemented method 'sub'");
    }
    
}