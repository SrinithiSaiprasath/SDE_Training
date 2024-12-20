#include <stdio.h>

struct school{
	int age;
	int rollno;
};
void solve(){
	struct school sc;
	sc.age = 19;
	sc.rollno = 82;
	printf("%d %d",sc.age,sc.rollno);
}
int main(){
	solve();
	return 0;
}
