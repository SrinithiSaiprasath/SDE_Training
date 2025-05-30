#include <stdio.h>

#define VAL 10

int getValue() {
    return VAL;
}

int main() {
    const int result = getValue();
    printf("The value is: %d\n", result);
    return 0;
}

// op 10