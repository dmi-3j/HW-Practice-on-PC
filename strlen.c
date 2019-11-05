#include <stdio.h>
#include <stdlib.h>
#include <string.h>
size_t strlenmy(char *s) {
    size_t count = 0;
    while (*s++) {
        ++count;
    }
    return count;
}
 int main(){
    char *s = (char*)malloc(sizeof(char)*1000);
    if (s == NULL)  {
        printf("Memory allocation error!");
        return -1;
    }
    printf("Enter the line: ");
    gets(s);
    int k = strlenmy(s);
    printf("String length: ");
    printf("%d", k);
    free(s);
    return 0;
}

