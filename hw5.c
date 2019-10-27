#include <stdio.h>
#include <stdlib.h>
void function1(void) {
int local01=14;
int local02=11;
int local03=34;
int *adr7 = &local01;
int *adr8 = &local02;
int *adr9 = &local03;
        printf("Adress of local variables: \n");
        printf("%p\n", adr7);
        printf("%p\n", adr8);
        printf("%p\n", adr9);
}
void function2(void) {
}
int main()
{
int global01=11,global02=15,global03=11;
int *x = (int*)malloc(sizeof(int)*10);
    if (x == NULL) {
            printf("Error!");
        return -1;
    }
int *y = (int*)malloc(sizeof(int)*10);
    if (y == NULL) {
            printf("Error!");
        return -1;
    }
int *adr1 = &global01;
int *adr2 = &global02;
int *adr3 = &global03;
int *adr4 = &x;
int *adr5 = &printf;
int *adr6 = &scanf;
int *adr10 = &function1;
int *adr11 = &function2;
int *adr12 = &y;
        printf("Adress of global variables: \n");
        printf("%p\n",adr1);
        printf("%p\n",adr2);
        printf("%p\n",adr3);
        printf("Adress of dynamic massive: \n");
        printf("%p\n",adr4);
        printf("%p\n",adr12);
        printf("Adress of printf: \n");
        printf("%p\n",adr5);
        printf("Adress of scanf: \n");
        printf("%p\n",adr6);
    function1();
        printf("Adress of functions: \n");
        printf("%p\n",adr10);
        printf("%p\n",adr11);
free(x);
    return 0;
}
