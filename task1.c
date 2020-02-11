#include <stdio.h>
#include <stdlib.h>

int fibonachi(int n) {
    if (n == 1 || n == 2) {
        return 1;
    }
        return fibonachi(n - 1) + fibonachi(n - 2);
    }
int main()
{
    int n;
    printf("Enter number n: ");
    scanf("%d", &n);
    if (n <= 0) {
        printf("Error! Try Again!");
        return 0;
    }
    printf("%d ", fibonachi(n));
    return 0;
}

