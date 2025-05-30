#include <stdio.h>

void solve() {
    int a[] = {1, 2, 3, 4, 5};
    int sum = 0;

    for (int i = 0; i < 5; i++) {
        if (i % 2 == 0) { // Even index
            sum += *(a + i);
        } else { // Odd index
            sum -= *(a + i);
        }
    }

    printf("%d", sum);
}

int main() {
    solve();
    return 0;
}

//op 3