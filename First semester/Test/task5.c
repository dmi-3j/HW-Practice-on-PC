#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void base64Encode (char* in, int inLen, char* out, int outLen) {
	static const char b64T[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	while (inLen && outLen) {
        *out++ = b64T[in[0] >> 2];
		*out++ = b64T[((in[0] << 4) + (--inLen ? (in[1]) >> 4 : 0)) & 0x3f];
		*out++ = b64T[((in[1] << 2) + (--inLen ? (in[2]) >> 6 : 0)) & 0x3f];
		*out++ = b64T[(in[2]) & 0x3f];
        if (inLen) {
            inLen--;
        }
        if (inLen) {
            in = in + 3;
        }
	}


}
int main() {
	char input[100000];
	printf("Please enter your line (Lenght is a multiple of 3):");
	gets(input);
	int len = strlen(input);
	if (len % 3 != 0) {
        printf("Error! Please try again!");
        return -1;
    }
	char output[150000];
	base64Encode(input, len, output, 150);
	printf("Your base64 code: ");
	printf("%s", output);
}
