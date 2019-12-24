#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include "listLibrary.h"
#include "hashTableLibrary.h"
#include <stdlib.h>
#include <crtdbg.h>
#define TABLE_SIZE 10000
#define new new( _NORMAL_BLOCK, __FILE__, __LINE__)
#ifdef _CRTDBG_MAP_ALLOC
inline void* __cdecl operator new(unsigned int s)
{
	return ::operator new(s, _NORMAL_BLOCK, __FILE__, __LINE__);
}
#endif
char* wordToNormalForm(char* buffer) {
	size_t i = 0;
	size_t j = 0;
	char word[50];
	char c = buffer[i];
	while (c != '\0') {
		if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')) {
			word[j] = c;
			j++;
		}
		i++;
		c = buffer[i];
	}
	word[j] = '\0';
	return word;
}
void printHashTable(struct HashTable* table) {
	size_t size = table->size;
	for (size_t i = 0; i < size; i++) {
		printList(table->strings[i]);
	}
}

int main() {
	_CrtSetReportMode(_CRT_WARN, _CRTDBG_MODE_FILE);
	_CrtSetReportFile(_CRT_WARN, _CRTDBG_FILE_STDOUT);
	struct HashTable* table = createHashTable(TABLE_SIZE, polynomialHash);
	char name[] = "D:\\Desktop\\TEXT.txt";
	FILE* fFile = fopen(name, "r");
	char buffer[50];
	while (fscanf(fFile, "%s", buffer) != EOF) {
		if (buffer[0] != '\0') {
			char word[50];
			strcpy(word, wordToNormalForm(buffer));
			int count = getValue(table, word);
			set(table, word, count + 1); 
		}
	}
	fclose(fFile); 
	printHashTable(table);
	freeHashTable(table);
	_CrtDumpMemoryLeaks();
	return 0; 
} 