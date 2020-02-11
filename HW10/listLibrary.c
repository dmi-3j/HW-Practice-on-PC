#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "listLibrary.h"

struct Node* createNode(int value, char* key) {
	struct Node* node = (struct Node*)malloc(sizeof(struct Node));
	if (node == NULL) {
		return NULL;
	}
	node->value = value;
	node->next = NULL;
	strcpy(node->key, key);
	return node;
}

struct List* createList() {
	struct List* list = (struct List*)malloc(sizeof(struct List));
	if (list == NULL) {
		return NULL;
	}
	list->head = NULL;
	list->length = 0;
	return list;
}

struct Node* findNode(struct List* list, char* key) {
	if (list->length == 0) {
		return NULL;
	}
	else {
		struct Node* node = list->head;
		while (node != NULL) {
			if (strcmp(key, node->key) == 0) {
				return node;
			}
			node = node->next;
		}
		return NULL;
	}
}

struct Node* getNode(size_t number, struct List* list) {
	if (list->length <= number) {
		return NULL;
	}
	size_t counter = 0;
	struct Node* currentNode = list->head;
	while (counter != number) {
		currentNode = currentNode->next;
		counter++;
	}
	return currentNode;
}

void insertToBegin(struct List* list, int value, char* key) {
	struct Node* node = createNode(value, key);
	node->next = list->head;
	list->head = node;
	list->length++;
}

void insertToEnd(struct List* list, int value, char* key) {
	if (list->length == 0) {
		list->head = createNode(value, key);
		list->length++;
	}
	else {
		struct Node* node = findNode(list, key);
		if (node == NULL) {
			getNode(list->length - 1, list)->next = createNode(value, key);
			list->length++;
		}
		else {
			node->value++;
		}
	}
}

printList(struct List* list) {
	struct Node* node = list->head;
	while (node) {
		printf("/%s: %i/ ", node->key, node->value);
		node = node->next;
		printf(" \n ");
	}
}

int getNodeNumber(struct List* list, char* key) {
	if (findNode(list, key) != NULL) {
		size_t number = 0;
		struct Node* node = list->head;
		while (strcmp(node->key, key) != 0) {
			number++;
			node = node->next;
		}
		return number;
	}
	return -1;
}

void deleteNode(struct List* list, char* key) {
	if (list->length != 0) {
		struct Node* delNode = findNode(list, key);
		if (delNode != NULL) {
			if (getNode(0, list) == delNode) {
				free(delNode);
				list->length--;
				return;
			}
			if (getNode(list->length - 1, list) == delNode) {
				getNode(list->length - 2, list)->next = NULL;
				free(delNode);
				list->length--;
				return;
			}
			struct Node* prevNode = getNodeByNumber(getNodeNumber(list, key) - 1, list);
			prevNode->next = delNode->next;
			free(delNode);
			list->length--;
		}
	}
}

struct Node* getNodeByNumber(size_t number, struct List* list) {
	if (list->length <= number) {
		return NULL;
	}
	size_t counter = 0;
	struct Node* currentNode = list->head;
	while (counter != number) {
		currentNode = currentNode->next;
		counter++;
	}
	return currentNode;
}

void freeList(struct List* list) {
	struct Node* node = list->head;
	while (node != NULL) {
		struct Node* delNode = node;
		node = node->next;
		free(delNode);
	}
	free(list);
}

bool isLoop(struct List* listPtr) {
	if (listPtr->length == 0) {
		return 0;
	}
	struct Node* fast = listPtr->head->next;
	struct Node* slow = listPtr->head;
	bool moveSlow = false;
	while (true) {
		if (fast == NULL) {
			return false;
		}
		if (fast == slow) {
			return true;
		}
		if (moveSlow) {
			slow = slow->next;
		}
		fast = fast->next;
		moveSlow = !moveSlow;
	}
} 