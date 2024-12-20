#include<stdio.h>

void solve(){
	int n=24;
	int l=0, r=100, ans=n;
	while(l<=r){
		int mid = (l+r)/2;
		if(mid*mid<=n){
			ans = mid;
			l = mid+1;
		}
		else{
			r = mid-1;
		}
	}
	printf("%d",ans);
}
int main(){
	solve();
	return 0;
}
