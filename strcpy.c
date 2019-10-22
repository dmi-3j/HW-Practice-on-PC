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
    printf("Enter the line: ");
    gets(s1);
    printf("\ns1: %s", s1);
    strcpymy(s1 , s2);
    printf("\n-----------\n");
    printf("s2: %s ",s2);
    free(s1);
    free(s2);
    return 0;
}
void strcpymy(char* s1, char* s2) {
    while ((*s2++ = *s1++));
}
