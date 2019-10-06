#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
system("chcp 1251");
system("cls");
int lnght;
char s[1000];
        printf("Enter your line: ");
    gets(s);
    lnght = strlen(s);
        printf("Length of your string: %d", lnght);
    
	return 0;
}
