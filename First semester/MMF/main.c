#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <malloc.h>
#include <sys/stat.h>
#include "mman.h"


void copyAdressStr(char **string, char *input) {
    size_t j = 0, jStr = 0, jInp = 0;
    while (input[j]) {
        if (input[j] == '\n') {
            string[jStr] = &input[jInp];
            jStr++;
            jInp = j + 1;
        }
        j++;
    }
}
int myStrCmp(void* p1, void* p2) {
	char* s1, * s2;
	s1 = *(char**)p1;
	s2 = *(char**)p2;
	return strcmp(s1, s2);
}

int main() {
    int mapFile = open("input.txt", O_RDWR, 0);
    if (mapFile < 0) {
        printf("Can't to open file or no such file.");
        exit(1);
    }
    FILE* fout = fopen("output.txt", "w");
    if (fout == NULL) {
        printf("Error!");
        exit(1);
    }
    struct stat info;
	fstat(mapFile, &info);
	size_t size = info.st_size;
    char* input = mmap(0, size, PROT_READ | PROT_WRITE, MAP_PRIVATE, mapFile, 0);
    int inputLen = strlen(input);

    int maxi = 0, helper = 0, n = 0;
    for (size_t i = 0; i < inputLen; i++) {
        if (input[i] == '\n') {
            n++;
        }
    }

    char **str = (char**)malloc(n * sizeof(char*));
    if (str == NULL) {
        printf("Error!");
        exit(1);
    }

    copyAdressStr(str, input);
    qsort(str, n, sizeof(char*), myStrCmp);

    for (size_t i = 0; i < n; i++) {
        size_t j = 0;
        while (str[i][j] != '\n') {
            fputc(str[i][j], fout);
            j++;
        }
    }

    close(mapFile);
    fclose(fout);
    free(str);
    munmap(input, inputLen);
    return 0;
}
