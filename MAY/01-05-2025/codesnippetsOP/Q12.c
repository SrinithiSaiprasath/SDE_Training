#include <stdio.h>
#define VAL 3 * (2 + 6)
void solve() {
    int a = 10 + VAL;
    printf("%d", a);
}
int main() {
    solve();
    return 0;
}

// 10 + 3*(2+6) = 10+ 3*8 = 10+24 = 34
// op 34
