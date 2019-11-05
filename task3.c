#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n,j,count = 0,k = 0;
    int *a = (int*)malloc(sizeof(int)*10000);
    if (a == NULL) {
        printf("Memory allocation error!");
        return -1;
    }
    int *b = (int*)malloc(sizeof(int)*10000);
    if (b == NULL) {
        printf("Memory allocation error!");
        return -1;
    }
    printf("Please enter amount of numbers: ");
    scanf("%d", &n);
    printf("Please enter numbers: ");
    for (int i = 0; i < n; i++) {
        scanf("%d", &a[i]);
        if ((a[i] < 0) || (a[i] > 255)) {
            printf("Error! Please try again!");
            return -1;
        }
    }
    for (int i = 0; i < n; i++) {
        printf("%d ", a[i]);
    }
    for (int i = 0;i < n;i++) {
        for (int j = i + 1;j < n;j++) {
            if (a[i] == a[j]) {
                a[j] = -1;
            }
        }
    }
    for (int i = 0;i < n;i++) {
        if (a[i] != -1) {
            count++;
        }
    }
    for (int i = 0;i < n;i++) {
        if (a[i] != -1) {
            b[k++] = a[i];
        }
    }
    printf("\nRepetitions removed!\n");
    for (int i = 0; i < count;i++) {
        printf("%d ", b[i]);
    }
	free(a);
	free(b);
    return 0;
}


