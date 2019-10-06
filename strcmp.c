#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
system("chcp 1251");
system("cls");
int a;
char s1[1000];
char s2[1000];
        printf("Enter the first line: ");
    gets(s1);
        printf("Enter the second line: ");
    gets(s2);
    a = strcmp(s1, s2);
        printf("%d", a);

	return 0;
}
