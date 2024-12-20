#include<stdio.h>

void solve(){
	int a=3, b=5;
	int t=a;
	a=b;
	b = t;
	printf("%d %d",a,b);
}
int main(){
	solve();
	return 0;
}
