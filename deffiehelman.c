#include <stdio.h>
#include <stdlib.h>

int pow(int a, int b, int c) {
    int res = 1;
    for (int i = 0; i < b; i++) {
        res = (res * a) % c;
    }
    return res;
}

int* GetKeys(int q, int alpha, int xa, int xb) {
    int ya = pow(alpha, xa, q);
    int yb = pow(alpha, xb, q);
    int k1 = pow(yb, xa, q);
    int k2 = pow(ya, xb, q);
    int* result = (int*)malloc(2 * sizeof(int));
    result[0] = k1;
    result[1] = k2;
    return result;
}

int main() {
    int q = 0;
    int alpha = 0;
    int xa = 0;
    int xb = 0;
    printf("Enter q: ");
    scanf("%d", &q);
    printf("Enter alpha: ");
    scanf("%d", &alpha);
    printf("Enter xa: ");
    scanf("%d", &xa);
    printf("Enter xb: ");
    scanf("%d", &xb);
    int* keys = GetKeys(q, alpha, xa, xb);
    printf("k1: %d\n", keys[0]);
    printf("k2: %d\n", keys[1]);
    free(keys);
    return 0;
}
