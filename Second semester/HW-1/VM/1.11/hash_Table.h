#ifndef HASH_TABLE_H_INCLUDED
#define HASH_TABLE_H_INCLUDED
#include "listHash.h"

struct HashTable {
	struct List** entry;
	size_t size;
	size_t(*hashFunc) (char* word);
};

struct HashTable* createHashTable(size_t(*hashFunc) (char* word), size_t k);
void addWord(struct HashTable* table, char* word, int val, size_t hash);
void deleteWord(struct HashTable* table, char* word);
size_t getData(struct HashTable* table, char* word);
void setData(struct HashTable*, char* word, size_t val);
void deleteTable(struct HashTable* table);
void statistics(struct HashTable* table);
int findWord(struct HashTable* table, char* word);

#endif // HASH_TABLE_H_INCLUDED
