#include <stdio.h>
#include <stdlib.h>

int main()
{
    char s[1000];
    printf("Enter your line: ");
    scanf("%s", s);
    int res = 0;
    size_t i = 0;
    while ((s[i] != 0) && ('0' <= s[i]) && (s[i] <= '9')) {
        if (('0' <= s[i]) && (s[i] <= '9')) {
            res = res * 10 + (s[i] - '0');
            i++;
        }
    }
    if (i == 0) {
        printf("Error! No numbers!");
        return -1;
    }
    printf("Result: %d", res);
    return 0;
}
