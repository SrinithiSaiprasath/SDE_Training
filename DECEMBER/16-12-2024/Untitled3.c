#include <stdio.h>
#include <conio.h>
struct School{
       int age ; 
       int rollno ;
};
 void Solve(){
      struct School sc ; 
      sc.age = 19  ; 
      sc.rollno = 82 ; 
      printf("%d %d", sc.age ,sc.rollno) ; 
}
int main(){
    Solve() ;
     return 0 ;
}
