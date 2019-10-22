#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    char *s1 = (char*)malloc(sizeof(char)*1000);
    char *s2 = (char*)malloc(sizeof(char)*1000);
    if ((s1 == NULL) || (s2 == NULL)) {
        printf("Memory allocation error!");
        return -1;
    }
    printf("Enter the first line: ");
    gets(s1);
    printf("Enter the second line: ");
    gets(s2);
    strcatmy(s1, s2);
    printf("Result: ");
    printf ("%s", s1);
    free(s1);
    free(s2);
    return 0;
}
void strcatmy(char *s1, char *s2) {
    while (*s1) {
        s1++;
    }
    while (*s2) {
        *s1 = *s2;
        s1++;
        s2++;
    }
}

