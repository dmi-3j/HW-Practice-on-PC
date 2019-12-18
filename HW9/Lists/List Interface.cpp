#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include "list_library.h"

const int printList = 1;
const int listCreate = 2;
const int addHead = 3;
const int addTail = 4;
const int addByValue = 5;
const int deleteElement = 6;
const int cleanList = 7;
const int exitWithClean = 9;
int main()
{

	struct List* list = NULL;

	int choice = -1;
	int value;
	int after;

	while (1) {
		printf("\tPrint List - 1\n");
		printf("\tCreate List - 2\n");
		printf("\tAdd Head - 3\n");
		printf("\tAdd Tail - 4\n");
		printf("\tAdd Element After (by value) - 5\n");
		printf("\tDelete Element (by value) - 6\n");
		printf("\tClean List - 7\n");
		printf("\tExit - 9\n\n");
		printf("\t\nEnter your choice: ");
		scanf_s("%d", &choice);
		switch (choice) {
			case printList:
				if (list != NULL) {
					print(list);
					printf("\n");
				}
				else {
					printf("\tList is NULL!\n\n");
					break;
				}
				break;
			case listCreate:
				list = createList();
				printf("\tList was created\n\n\n");
				break;
			case addHead:
				if (list != NULL) {
					printf("\n\tYour value: ");
					scanf_s("%d", &value);
					addToHead(list, value);
				}
				else {
					printf("List is NULL!\n\n");
					break;
				}
				break;
			case addTail:
				if (list != NULL) {
					printf("\tYour value: ");
					scanf_s("%d", &value);
					addToTail(list, value);
				}
				else {
					printf("List is NULL!\n\n");
					break;
				}
				break;
			case addByValue:
				if (list != NULL) {
					printf("\tAfter element with value: ");
					scanf_s("%d", &after);
					printf("\n\tAdd value: ");
					scanf_s("%d", &value);
					addAfterFromValue(list, after, value);
				}
				else {
					printf("List is NULL!\n\n");
					break;
				}
				break;
			case deleteElement:
				if (list != NULL) {
					printf("\n\tYour value: ");
					scanf_s("%d", &value);
					deleteNodeByValue(list, value);
				}
				else {
					printf("List is NULL!\n\n");
					break;
				}
				break;
			case cleanList:
				if (list != NULL) {
					clean(list);
					printf("\tlist was cleaned\n\n");
				}
				else {
					printf("List is NULL!\n\n");
					break;
				}
				break;
			case exitWithClean:
				free(list);
				exit(0); 
				break;
			default:
				printf("\tOption not listed\n\n");
				break;
		}
	}
	return 0;
}
