#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
system("chcp 1251");
system("cls");
char s1[1000];
char s2[1000];
char s3[1000];
        printf("Enter the first line: ");
    gets(s1);
        printf("\n");
    strcpy(s2, s1);
    strcpy(s3, "The contents of the first line have been successfully copied to the second line!\n");

        printf("First line: %s\n", s1);
        printf("Second line: %s\n\n", s2);
        printf(s3);

	return 0;
}
