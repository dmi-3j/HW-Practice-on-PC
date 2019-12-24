#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
#include <limits.h>
#include <time.h>
#include "listLibrary.h"
#include "hashTableLibrary.h"


size_t polynomialHash(char* key, size_t size) {
	long long int hash = 0;
	int coefficient = 31;
	int multiplier = 1;
	size_t i = 0;
	while (key[i]) {
		hash += (key[i] - 'a' + 1) * coefficient * multiplier;
		multiplier *= coefficient;
		i++;
	}
	return compressionMap(hash, size);
}
size_t compressionMap(long long int hash, size_t size) {
	size_t index = 0;
	int a = 71317;
	int b = 43981;
	index = abs(a * hash + b) % size;
	return index;
}

size_t symbolSumHash(char* key, size_t size) {
	long long int hash = 0;
	while (*key) {
		hash += *key;
		key++;
	}
	compressionMap(hash, size);
}
size_t constantHash(char* key, size_t size) {
	long long int hash = 56273901378347;
	return compressionMap(hash, size);
}



struct HashTable* createHashTable(size_t size, size_t(*hashFunction)(char* key)) {
	struct HashTable* table = (struct HashTable*)malloc(sizeof(struct HashTable));
	table->strings = (struct List*)malloc(size * sizeof(struct List*));
	for (size_t i = 0; i < size; i++)
	{
		table->strings[i] = createList();
	}
	table->size = size;
	table->hashFunction = hashFunction;
	return table;
}

void freeHashTable(struct HashTable* table) {
	for (size_t i = 0; i < table->size; i++) {
		freeList(table->strings[i]);
	}
	free(table->strings);
	free(table);
}

void insertElementToTable(struct HashTable* table, char* key) {
	size_t index = table->hashFunction(key, table->size);
	insertToEnd(table->strings[index], 1, key);
}

struct Node* findElement(struct HashTable* table, char* key) {
	size_t index = table->hashFunction(key, table->size);
	return findNode(table->strings[index], key);
}

void deleteElementFromTable(struct HashTable* table, char* key) {
	struct Node* node = findElement(table, key);
	if (node != NULL) {
		size_t index = table->hashFunction(key, table->size);
		deleteNode(table->strings[index], key);
	}
}

int getValue(struct HashTable* table, char* key) {
	struct Node* node = findElement(table, key);
	if (node != NULL) {
		return node->value;
	}
	else {
		return 0;
	}
}

void set(struct HashTable* table, char* key, int value) {
	struct Node* node = findElement(table, key);
	if (node != NULL) {
		node->value = value;
	}
	else {
		size_t index = table->hashFunction(key, table->size);
		insertToBegin(table->strings[index], value, key);
	}
}

int numberOfChains(struct HashTable* table) {
	size_t size = table->size;
	size_t count = 0;
	for (size_t i = 0; i < size; i++) {
		if (table->strings[i]->length > 0) {
			count++;
		}
	}
	return count;
}

int numberOfElemnts(struct HashTable* table) {
	size_t size = table->size;
	size_t number = 0;
	for (size_t i = 0; i < size; i++) {
		number += table->strings[i]->length;
	}
	return number;
}

int minChainLength(struct HashTable* table) {
	size_t size = table->size;
	size_t min = _UI32_MAX;
	for (size_t i = 0; i < size; i++) {
		if (table->strings[i]->length < min && table->strings[i]->length > 0) {
			min = table->strings[i]->length;
		}
	}
	return min;
}

int maxChainLength(struct HashTable* table) {
	size_t size = table->size;
	size_t max = table->strings[0]->length;
	for (size_t i = 0; i < size; i++) {
		if (table->strings[i]->length > max) {
			max = table->strings[i]->length;
		}
	}
	return max;
}

int averageChainLength(struct HashTable* table) {
	size_t size = table->size;
	size_t sumLength = 0;
	size_t numberOfChains = 0;
	for (size_t i = 0; i < size; i++) {
		if (table->strings[i]->length > 0) {
			sumLength += table->strings[i]->length;
			numberOfChains++;
		}
	}
	return sumLength / numberOfChains;
}

void getInformation(struct HashTable* hashT, struct Information* inf) {
	inf->numberOfElements = numberOfElemnts(hashT);
	inf->numberOfChains = numberOfChains(hashT);
	inf->minChainLength = minChainLength(hashT);
	inf->maxChainLength = maxChainLength(hashT);
	inf->averageChainLength = averageChainLength(hashT);
} 