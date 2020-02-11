#ifndef HASHTABLE_LIBRARY_INCLUDED
#define HASHTABLE_LIBRARY_INCLUDED

struct HashTable {
	struct List** strings;
	size_t(*hashFunction)(char* key, size_t size);
	size_t size;
};

struct Information {
	size_t numberOfElements;
	size_t numberOfChains;
	size_t minChainLength;
	size_t maxChainLength;
	size_t averageChainLength;
};
size_t constantHash(char* key, size_t size);
size_t symbolSumHash(char* key, size_t size);
size_t compressionMap(long long int hash, size_t size);
size_t polynomialHash(char* key, size_t size);

int numberOfChains(struct HashTable* table);
int numberOfElemnts(struct HashTable* table);
int minChainLength(struct HashTable* table);
int maxChainLength(struct HashTable* table);
int averageChainLength(struct HashTable* table);

void getInformation(struct HashTable* hashT, struct Information* inf);
struct HashTable* createHashTable(size_t size, size_t(*hashFunction)(char* key));
void freeHashTable(struct HashTable* table);
void insertElementToTable(struct HashTable* table, char* word);
void deleteElementFromTable(struct HashTable* table, char* key);
struct Node* findElement(struct HashTable* table, char* key);
int getValue(struct HashTable* table, char* key);
void set(struct HashTable* table, char* key, int value);


#endif HASHTABLE_LIBRARY_INCLUDED