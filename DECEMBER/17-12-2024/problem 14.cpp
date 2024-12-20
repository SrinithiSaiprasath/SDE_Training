#include<stdio.h>

void solve(){
	int ch =2;
	switch(ch){
		case 1:
			printf("1");
		case 2:
			printf("2");
		case 3:
			printf("3");
		default:
			printf("None");
	}
}
int main(){
	solve();
	return 0;
}
