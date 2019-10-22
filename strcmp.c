#include <stdio.h>
#include <stdlib.h>

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
    int k = strcmpmy(s1, s2);
    printf("Result: %d", k);
	free(s1);
	free(s2);
    return 0;
}
int strcmpmy(char *s1, char *s2) {
    while(*s1 && *s2 && (*s1 == *s2))
    {
        s1++; s2++;
    }
    if (*s1 > *s2) {
        return 1;
    }
    if (*s1 < *s2) {
        return -1;
    }
    return 0;
}
