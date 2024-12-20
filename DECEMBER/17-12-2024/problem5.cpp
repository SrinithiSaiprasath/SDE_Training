#include<stdio.h>
#define VAL 5
int getInput(){
	return VAL;
}
void solve(){
	const int x = getInput();
	printf("%d",x);
}
int main(){
	solve();
	return 0;
}
