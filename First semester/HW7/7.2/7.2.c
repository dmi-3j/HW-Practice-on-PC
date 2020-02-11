#include <stdio.h>

int main()
{
    short x = 1;
    int  c = *((char *) &x);
    if (c == 1) {
        printf("little-endian");
    }
    else {
        printf("big-endian");
    }
    return 0;
}




