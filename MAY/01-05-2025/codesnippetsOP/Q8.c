#include <stdio.h>
void solve() {
    int x = 1, y = 2;
    printf(x > y ? "Greater" : x == y ? "Equal" : "Lesser");
}
int main() {
    solve();
    return 0;
}

//op less