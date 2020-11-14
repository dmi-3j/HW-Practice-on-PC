#include <stdio.h>
#include <memory.h>
#include <stdlib.h>
#include <string.h>
#include "hash_Table.h"
#include "listHash.h"

struct HashTable* createHashTable(size_t(*hashFunc) (char* word), size_t k) {
	struct HashTable* table = (struct HashTable*) malloc(sizeof(struct HashTable));
	if (table == NULL) {
		printf("ERROR");
		exit(1);
	}
	table->size = k;
	table->hashFunc = hashFunc;
	table->entry = (struct List**) malloc(k * sizeof(struct List*));
	if (table->entry == NULL) {
		printf("ERROR");
		exit(1);
	}
	for (size_t i = 0; i < k; i++) {
		table->entry[i] = createList();
		if (table->entry[i] == NULL) {
			printf("ERROR");
			exit(1);
		}
	}
	return table;
}

void addWord(struct HashTable* table, char* word, int val, size_t hash) {
	insertToBegin(table->entry[hash], createNode(val, word));
	return;
}

void deleteWord(struct HashTable* table, char* word) {
	size_t hash = table->hashFunc(word);
	struct Node* node = findEl(table->entry[hash], word);
	if (node) {
		deleteNode(table->entry[hash], node);
	}
	return;
}

int findWord(struct HashTable* table, char* word) {
	size_t hash = table->hashFunc(word);
	struct Node* node = findEl(table->entry[hash], word);
	if (node) {
        return node->data;
	}
	return 0;
}

size_t getData(struct HashTable* table, char* word) {
	size_t hash = table->hashFunc(word);
	struct Node* node = findEl(table->entry[hash], word);
	if (!node) {
		return 0;
	}
	else {
		return node->data;
	}
}

void setData(struct HashTable* table, char* word, size_t val) {
	size_t hash = table->hashFunc(word);
	struct Node* node = findEl(table->entry[hash], word);
	if (node) {
		node->data = val;
	}
	else {
		addWord(table, word, val, hash);
	}
	return;
}

void deleteTable(struct HashTable* table) {
	for (size_t i = 0; i < table->size; i++) {
		clearList(table->entry[i]);
	}
	free(table->entry);
	free(table);
	return;
}

void statistics(struct HashTable* table) {
	size_t el = 0, cell = 0, lenS = 0, lenMin = table->size, lenMax = 0;
	for (size_t i = 0; i < table->size; i++) {
		size_t len = (table->entry[i])->length;
		if (len != 0) {
			cell++;
		}
		el += len;
		if (len < lenMin && len != 0) {
			lenMin = len;
		}
		if (len > lenMax) {
			lenMax = len;
		}
	}
	lenS = el / cell;
	printf("Number cells = %d\n", cell);
	printf("Number elements = %d\n", el);
	printf("Min length of list = %d\n", lenMin);
	printf("Max length of list = %d\n", lenMax);
	printf("Average length of list = %d\n", lenS);
}
