#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
#include "listHash.h"

struct List* createList() {
	struct List* list = (struct List*) malloc(sizeof(struct List));
	if (list == NULL) {
		printf("ERROR");
		exit(1);
	}
	list->head = NULL;
	list->end = NULL;
	list->length = 0;
	return list;
}

struct Node* createNode(int val, char* word) {
	struct Node* node = (struct Node*) malloc(sizeof(struct Node));
	if (node == NULL) {
		printf("ERROR");
		exit(1);
	}
	node->data = val;
	strcpy(node->word, word);
	node->next = NULL;
	return node;
}

void insertToBegin(struct List* list, struct Node* node) {
	node->next = list->head;
	list->head = node;
	list->length++;
	if (list->length == 1) {
		list->end = node;
	}
}

void insertAfterEl(struct List* list, struct Node* after, struct Node* newNode) {
	newNode->next = after->next;
	after->next = newNode;
	list->length++;
	if (list->end == after) {
		list->end = newNode;
	}
}

struct Node* getN(struct List* list, size_t n) {
	struct Node* curr = list->head;
	size_t index = 0;
	while (curr) {
		if (index == n) {
			return curr;
		}
		curr = curr->next;
		index++;
	}
	return NULL;
}
void deleteNode(struct List* list, struct Node* toDelete) {
	struct Node* curr = list->head;
	while (curr) {
		if ((curr->next) == toDelete) {
			curr->next = curr->next->next;
			free(toDelete);
			return;
		}
		curr = curr->next;
	}
}

void clearList(struct List* list) {
	struct Node* curr = list->head;
	while (curr) {
		struct Node* tmp = curr;
		curr = curr->next;
		free(tmp);
	}
	list->head = NULL;
	list->end = NULL;
	list->length = 0;
	free(list);
}

void printList(struct List* list) {
	struct Node* curr = list->head;
	while (curr != NULL) {
		printf("%d ", curr->data);
		curr = curr->next;
	}
	printf("\n");
}

void printNode(struct List* list, size_t n) {
	struct Node* node = getN(list, n);
	printf("%d\n", node->data);

}

void createCycle(struct List* list, int from, int to) {
	if ((list->head->next) == NULL) {
		printf("Few elements\n");
		return;
	}
	else {
		struct Node* node = list->head;
		struct Node* nodeFrom = NULL;
		while (node->next)
		{
			if (node->data == from) {
				nodeFrom = node;
			}
			if (node->data == to && nodeFrom) {
				node->next = nodeFrom;
				return;
			}
			node = node->next;
		}
		if (node->data == to) {
			node->next = nodeFrom;
		}
	}
}

int checkCycle(struct List* list) {
	struct Node* slow = createNode(list->head->data, " ");
	struct Node* fast = createNode(list->head->data, " ");
	while (1) {
		if ((slow->next == NULL) || (fast->next->next == NULL)) {
			return 0;
		}
		slow = slow->next;
		fast = fast->next->next;
		if (slow == fast) {
			return 1;
		}
	}
}

struct Node* findEl(struct List* list, char* word) {
	struct Node* curr = list->head;
	while (curr) {
		if (!strcmp(curr->word, word)) {
			return curr;
		}
		curr = curr->next;
	}
	return 0;
}
