#include <stdlib.h>
#include <string.h>
#include <limits.h>
#include <time.h>
#include <stdio.h>

const size_t bigNum = 1000000;
int randomNumber = 823;

void countSort(int *b, size_t n) {
	int *count = calloc(bigNum, sizeof(int));
	if (count == NULL) {
		printf("Memory allocation error!");
		return;
	}
	for (size_t i = 0; i < n; i++) {
		count[b[i]]++;
	}
	size_t j = 0;
	for (size_t i = 0; i < bigNum; i++) {
		while (count[i]) {
			b[j++] = i;
			count[i]--;
		}
	}
}

int findMin(int *b, size_t n) {
	int Min = b[0], nMin = 0;
	for (size_t i = 1; i < n; ++i) {
		if (b[i] < Min) {
			Min = b[i];
			nMin = i;
		}
	}
	return nMin;
}
void swap(int *a, int *b) {
	int c = *a;
	*a = *b;
	*b = c;
}
void insertSort(int *b, size_t n) {
	for (size_t i = 0; i < n; ++i) {
		size_t t = findMin(b + i, n - i) + i;
		swap(&b[t], &b[i]);
	}
	return;
}

void quickSortT(int *b, size_t l, size_t r) {
	if ((l + 1) == r || l == r)
		return;

	size_t m = (r - l) / 2 + l, k = 0;
	swap(&b[l], &b[m]);
	size_t i = l + 1;
	while (i < (r - k)) {
		if (b[i] > b[i - 1]) {
			swap (&b[i], &b[r - (++k)]);
		} else {
			swap (&b[i], &b[i - 1]);
			i++;
		}
	}
	quickSortT(b, l, i - 1);
	quickSortT(b, i, r);
}

void random(int *b, size_t n) {
	srand(randomNumber);
	for (size_t i = 0; i < n; ++i){
		b[i] = rand() % bigNum;
	}
}

void quickSort(int *b, size_t n) {
	quickSortT(b, 0, n);
}

int main() {
	size_t n[] = {5, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
	void (*sorts[])(int *, size_t) = {&countSort, &quickSort, &insertSort};
	char *names[]= {"N","NlogN", "N^2"};
	clock_t begin, end;
	size_t numS = sizeof(sorts)/sizeof(sorts[0]);
	size_t numN = sizeof(n)/sizeof(n[0]);
	int *data = malloc(n[numN - 1] * sizeof(int));
	if (data == NULL) {
		printf("Memory allocation error!");
		return 0;
	}


	for (size_t j = 0; j < numS; ++j){
		printf(" %s \n", names[j]);
		for (size_t i = 0; i < numN; i++) {
			random(data, n[i]);
			begin = clock();
			sorts[j](data, n[i]);
			end = clock();
			printf("Size = %d Time = %3.5f\n", n[i], (float) (end - begin)/CLOCKS_PER_SEC);
		}
	printf("\n\n");
	}
	free(data);
}
