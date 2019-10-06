#include <stdio.h>
#include <stdlib.h>

int main()
{
char s1[1000];
char s2[1000];

        printf("Enter the first line: ");
    gets(s1);
        printf("Enter the second line: ");
    gets(s2);
    strcat(s1, s2);
        printf(s1);
        printf("\n");
        printf("These lines are combined by the function strcat.");

	return 0;
}
