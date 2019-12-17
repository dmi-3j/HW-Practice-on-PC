#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

struct Node
{
	int value;
	struct Node *next;
};

struct List
{
	struct Node *head;
};
struct Node* createNode(int value);
struct List* createList();
void addToHead(struct List* list, int value);
void addToTail(struct List* list, int value);
void addAfterFromValue(struct List* list, int after, int value);
void deleteNodeByValue(struct List* list, int value);
void Clean(struct List* list);
void makeLOOP(struct List* list, int from, int to);
int Loop(struct List* list);
void Print(struct List* list);
struct Node* createNode(int value)
{
	Node* node = (Node*)malloc(sizeof(Node));
	if (node != NULL) {
		node->value = value;
		node->next = NULL;
		return node;
	}
}
struct List* createList()
{
	List* list = (List*)malloc(sizeof(List));
	list->head = NULL;
	return list;
}
void Print(List* list)
{
	if (list->head && Loop(list))
	{
		printf("List have loop\n");
		Node* node = list->head;
		while (node->next)
		{
			printf("%d ", node->value);
			node = node->next;
		}
		printf("%d ", node->value);
	}
	else {
		Node* node = list->head;
		while (node->next)
		{
			printf("%d ", node->value);
			node = node->next;
		}
		printf("%d ", node->value);
	}

}
void addToHead(List *list, int value)
{
	Node *node = (Node*)malloc(sizeof(Node));
	node->value = value;
	node->next = list->head;
	list->head = node;
}

void addToTail(List *list, int value)
{
	Node *node = (Node*)malloc(sizeof(Node));
	if (list->head)
	{
		node = list->head;
		while (node->next)
		{
			node = node->next;
		}
		node->next = createNode(value);
	}
	else {
		list->head = createNode(value);
	}
}
void addAfterFromValue(List *list, int after, int value) {
	Node *node = list->head;
	while (node)
	{
		if (node->value == after)
		{
			Node* newNode = createNode(value);
			newNode->next = node->next;
			node->next = newNode;
			break;
		}
		node = node->next;
	}
}
void Clean(List* list)
{
	Node *node = list->head;
	while (node)
	{
		Node *removable = node;
		node = node->next;
		free(removable);
	}
	list->head = NULL;
	free(list);
}

int Loop(List *list)
{
	Node *one = createNode(list->head->value);
	Node *two = createNode(list->head->value);
	while (1)
	{
		if ((one->next == NULL) || (two->next->next == NULL))
		{
			return 0;
		}
		one = one->next;
		two = two->next->next;
		if (one == two)
		{
			return 1;
		}
	}
}


void deleteNodeByValue(List* list, int value)
{
	if (list->head->next)
	{
		Node *before = (Node*)malloc(sizeof(Node));
		before->value = list->head->value;
		before = list->head;
		Node *after = (Node*)malloc(sizeof(Node));
		after->value = list->head->value;
		after = list->head->next;

		while (after)
		{
			if (after->value == value)
			{
				Node *removable = (Node*)malloc(sizeof(Node));
				removable = after;
				if (removable->next)
				{
					after = removable->next;
					before->next = after;
					free(removable);
				}
				else {
					after = NULL;
					before->next = after;
					free(removable);
				}
			}
			else {
				after = after->next;
				before = before->next;
			}
		}
	}
	else {
		if (list->head->value == value)
		{
			Node* removable = (Node*)malloc(sizeof(Node));
			removable->next = NULL;
			list->head = removable->next;
			free(removable);
			return;
		}
	}
	if (list->head->value == value)
	{
		Node* removable = (Node*)malloc(sizeof(Node));
		removable = list->head;
		list->head = removable->next;
		free(removable);
	}
}
void makeLOOP(struct List* list, int from, int to)
{
	if (!list->head->next)
	{
		printf("Need more elements\n\n");
		return;
	}
	else {
		struct Node* node = list->head;
		struct Node* nodeFrom = NULL;
		while (node->next)
		{
			if (node->value == from)
			{
				nodeFrom = node;
			}
			if (node->value == to && nodeFrom)
			{
				node->next = nodeFrom;
				return;
			}
			node = node->next;
		}
		if (node->value == to)
		{
			node->next = nodeFrom;
		}
	}
}
