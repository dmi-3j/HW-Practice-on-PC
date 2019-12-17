#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include "list_library.h"

const int ACT_1 = 1;
const int ACT_2 = 2;
const int ACT_3 = 3;
const int ACT_4 = 4;
const int ACT_5 = 5;
const int ACT_6 = 6;
const int ACT_7 = 7;
const int ACT_8 = 8;
const int ACT_9 = 9;
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
		case ACT_1:
			if (list != NULL) {
				Print(list);
				printf("\n");
			}
			else {
				printf("\tList is NULL!\n\n");
				break;
			}
			break;
		case ACT_2:
			list = createList();
			printf("\tList was created\n\n\n");
			break;
		case ACT_3:
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
		case ACT_4:
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
		case ACT_5:
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
		case ACT_6:
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
		case ACT_7:
			if (list != NULL) {
				Clean(list);
				printf("\tlist was cleaned\n\n");
			}
			else {
				printf("List is NULL!\n\n");
				break;
			}
			break;
		case ACT_9:
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
