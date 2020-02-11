#include <stdio.h>
#include <stdlib.h>

int main()
{
    int N, j, count = 0, k = 0;
    printf("Please enter amount of numbers: ");
    scanf("%d", &N);
    int *a = (int*)malloc(sizeof(int)*N);
    if (a == NULL) {
        printf("Memory allocation error!");
        return -1;
    }
    int *b = (int*)malloc(sizeof(int)*N);
    if (b == NULL) {
        printf("Memory allocation error!");
        return -1;
    }
    printf("Please enter numbers: ");
    for (int i = 0; i < N; i++) {
        scanf("%d", &a[i]);
    }
    for (int i = 0; i < N; i++) {
        printf("%d ", a[i]);
    }
    for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
            if (a[i] == a[j]) {
                a[j] = -1;
            }
        }
    }
    for (int i = 0; i < N; i++) {
        if (a[i] != -1) {
            count++;
        }
    }
    for (int i = 0; i < N; i++) {
        if (a[i] != -1) {
            b[k++] = a[i];
        }
    }
    printf("\nRepetitions removed!\n");
    for (int i = 0; i < count; i++) {
        printf("%d ", b[i]);
    }
    free(a);
    free(b);
    return 0;
}


