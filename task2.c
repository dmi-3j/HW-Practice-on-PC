#include <stdio.h>
#include <stdlib.h>

int main()
{
    char *s =(char*)malloc(sizeof(int)*10000);
    if (s == NULL) {
        printf("Memory allocation error!");
        return -1;
    }
    scanf("%s", s);
    int k = strlen(s);
    for (int i = 0;i<k;i++){
    switch (s[i]){
        case '1':
            printf("1");
            break;
        case '2':
            printf("2");
            break;
        case '3':
            printf("3");
            break;
        case '4':
            printf("4");
            break;
        case '5':
            printf("5");
            break;
        case '6':
            printf("6");
            break;
        case '7':
            printf("7");
            break;
        case '8':
            printf("8");
            break;
        case '9':
            printf("9");
            break;
        case '0':
            printf("0");
            break;
        }

   }
    return 0;
}
