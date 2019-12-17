#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include "list_library.h"

const int ACTION01 = 1;
const int ACTION02 = 2;
const int ACTION03 = 3;
const int ACTION04 = 4;
const int ACTION05 = 5;
const int ACTION06 = 6;
const int ACTION07 = 7;
const int ACTION08 = 8;
const int ACTION09 = 9;
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
		case ACTION01:
			if (list != NULL) {
				print(list);
				printf("\n");
			}
			else {
				printf("\tList is NULL!\n\n");
				break;
			}
			break;
		case ACTION02:
			list = createList();
			printf("\tList was created\n\n\n");
			break;
		case ACTION03:
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
		case ACTION04:
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
		case ACTION05:
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
		case ACTION06:
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
		case ACTION07:
			if (list != NULL) {
				clean(list);
				printf("\tlist was cleaned\n\n");
			}
			else {
				printf("List is NULL!\n\n");
				break;
			}
			break;
		case ACTION09:
			free(list);
			exit(0); 
			break;
		default:
			printf("\tOption not listed\n\n");
			break;
		}
		free(list);													//Не будет утечки памяти? В 9 пункте при выходе лист очищается
	}
	return 0;
}
