#include <stdio.h>

struct School {
    int age, rollNo;
};

void solve() {
    struct School sc;
    sc.age = 19;       // Assign age
    sc.rollNo = 82;    // Assign roll number
    printf("%d %d", sc.age, sc.rollNo); // Print age and roll number
}

int main() {
    solve();
    return 0;
}

//op 19 82