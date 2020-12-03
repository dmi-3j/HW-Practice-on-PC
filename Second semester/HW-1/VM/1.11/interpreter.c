#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <malloc.h>
#include "hash_Table.h"
#define NO_CMD 50
#define MEMORY_SIZE 1024*1024
#define MAX_STACK_SIZE 2048
#define STR_SIZE 30
#define MAX_OPERATIONS 300

struct Stack {
    int32_t data[MAX_STACK_SIZE];
    int32_t size;
};

struct State {
    struct Stack stack;
    int32_t* memory;
};

struct CMD {
    int32_t opcode;
    int32_t arg;
};

struct Program {
    struct CMD* operations;
    size_t progSize;
    struct HashTable* lables;
};

struct Interpreter {
    struct Program prog;
    struct State state;
};

enum Opcodes {
    ld,
    st,
    ldc,
    add,
    sub,
    cmp,
    jmp,
    br,
    ret
};

size_t strToCmd(char* string) {
    if (strstr(string, "ld") && !strstr(string, "ldc")) {
        return ld;
    }
    if (strstr(string, "st")) {
        return st;
    }
    if (strstr(string, "ldc")) {
        return ldc;
    }
    if (strstr(string, "add")) {
        return add;
    }
    if (strstr(string, "sub")) {
        return sub;
    }
    if (strstr(string, "cmp")) {
        return cmp;
    }
    if (strstr(string, "jmp")) {
        return jmp;
    }
    if (strstr(string, "br")) {
        return br;
    }
    if (strstr(string, "ret")) {
        return ret;
    }
    return NO_CMD;
}

size_t hash(char* word) {
    size_t hash = 0;
    while (*word) {
        hash += (unsigned char)(*word);
        hash += (hash << 10);
        hash ^= (hash >> 6);
        word++;
    }
    hash += (hash << 3);
    hash ^= (hash >> 11);
    hash += (hash << 15);
    return hash % MAX_OPERATIONS;
}

void lable(FILE* file, struct Interpreter* inerpreter) {
    char str[STR_SIZE] = { 0 };
    size_t num = 0;
    while (fgets(str, STR_SIZE, file)) {
        if (strToCmd(str) == NO_CMD) {
            continue;
        }
        size_t sp = 0;
        while (strchr(str, ':')) {
            size_t spIn = 0;
            size_t len = strcspn(str, ":");
            char name[len - sp];
            for (size_t i = sp; i < len; i++) {
                if (str[i] == ' ') {
                    spIn++;
                    continue;
                }
                name[i - sp] = str[i];
                str[i] = ' ';
                spIn++;
            }
            name[len - sp] = 0;
            str[len] = ' ';
            sp = sp + spIn + 2;
            addWord(inerpreter->prog.lables, name, num, hash(name));
            for (size_t i = 0; i < len; i++) {
                name[i] = 0;
            }
        }
        num++;
        for (size_t i = 0; i < sizeof(str); i++) {
            str[i] = 0;
        }
    }
    inerpreter->prog.progSize = num;
}

void byteCode(FILE* file, struct Interpreter* interpreter) {
    char str[STR_SIZE] = { 0 };
    size_t k = 0;
    while (fgets(str, STR_SIZE, file)) {
        size_t t = 0;
        while (str[t] != '\n') {
            t++;
        }
        str[t] = 0;
        size_t cmd = strToCmd(str);
        switch (cmd) {
            case ld: {
                char* ar = strstr(str, "ld") + 3;
                int32_t argument = atoi(ar);
                interpreter->prog.operations[k].arg = argument;
                interpreter->prog.operations[k].opcode = ld;
                break;
            }
            case st: {
                char* ar = strstr(str, "st") + 3;
                int32_t argument = atoi(ar);
                interpreter->prog.operations[k].arg = argument;
                interpreter->prog.operations[k].opcode = st;
                break;
            }
            case ldc: {
                char* ar = strstr(str, "ldc") + 4;
                int32_t argument = atoi(ar);
                interpreter->prog.operations[k].arg = argument;
                interpreter->prog.operations[k].opcode = ldc;
                break;
            }
            case add: {
                interpreter->prog.operations[k].opcode = add;
                break;
            }
            case sub: {
                interpreter->prog.operations[k].opcode = sub;
                break;
            }
            case cmp: {
                interpreter->prog.operations[k].opcode = cmp;
                break;
            }
            case jmp: {
                char* ar = strstr(str, "jmp") + 4;
                interpreter->prog.operations[k].arg = findWord(interpreter->prog.lables, ar);
                interpreter->prog.operations[k].opcode = jmp;
                break;
            }
            case br: {
                char* ar = strstr(str, "br") + 3;
                interpreter->prog.operations[k].arg = findWord(interpreter->prog.lables, ar);
                interpreter->prog.operations[k].opcode = br;
                break;
            }
            case ret: {
                interpreter->prog.operations[k].opcode = ret;
                break;
            }
            default: {
                continue;
            }
        }
        k++;
    }
}

void execute(struct Interpreter* interpreter) {
    struct Stack* stack = &(interpreter->state.stack);
    int32_t k = 0;
    while (1) {
        int32_t argument = interpreter->prog.operations[k].arg;
        switch (interpreter->prog.operations[k].opcode) {
        case ld:
            if (stack->size >= MAX_STACK_SIZE) {
                printf("ERROR max stack");
                return;
            }
            stack->data[stack->size] = interpreter->state.memory[argument];
            stack->size++;
            break;
        case st:
            interpreter->state.memory[argument] = stack->data[stack->size - 1];
            stack->size = stack->size - 1;
            break;
        case ldc:
            if (stack->size >= MAX_STACK_SIZE) {
                printf("ERROR max stack");
                return;
            }
            stack->data[stack->size] = argument;
            stack->size++;
            break;
        case add:
            stack->data[stack->size - 2] = stack->data[stack->size - 2] + stack->data[stack->size - 1];
            stack->size = stack->size - 1;
            break;
        case sub:
            stack->data[stack->size - 2] = stack->data[stack->size - 1] - stack->data[stack->size - 2];
            stack->size = stack->size - 1;
            break;
        case cmp:
            if (stack->data[stack->size - 2] > stack->data[stack->size - 1]) {
                stack->data[stack->size] = -1;
            } else if (stack->data[stack->size - 2] < stack->data[stack->size - 1]) {
                stack->data[stack->size] = 1;
            } else {
                stack->data[stack->size] = 0;
            }
            stack->size++;
            break;
        case jmp:
            k = argument - 1;
            break;
        case br:
            if (stack->data[stack->size - 1] != 0) {
                k = argument - 1;
            }
            break;
        case ret:
            printf("ret %d", stack->data[stack->size - 1]);
            return;
            break;
        default:
            printf("ERROR");
        }
        k++;
    }
}

int main() {
    struct Interpreter interpreter;
    struct Interpreter* interp = &interpreter;
    interp->state.stack.size = 0;
    interp->state.memory = (int32_t*) malloc(MEMORY_SIZE * sizeof(int32_t));
    if (interp->state.memory == NULL) {
		printf("ERROR");
		exit(1);
	}
    interp->prog.operations = (struct CMD*) malloc(MAX_OPERATIONS * sizeof(struct CMD));
    if (interp->prog.operations == NULL) {
		printf("ERROR");
		exit(1);
    }
    interp->prog.lables = createHashTable(hash, MAX_OPERATIONS);
    if (interp->prog.lables == NULL) {
        printf("ERROR creating hash table");
        exit(1);
    }
    FILE* file = fopen("test.txt", "rt");
    if (!file) {
        printf("ERROR file opening");
        exit(1);
    }
    lable(file, interp);
    fclose(file);
    file = fopen("test.txt", "rt");
    if (!file) {
        printf("ERROR file opening");
        exit(1);
    }
    byteCode(file, interp);
    fclose(file);
    execute(interp);
    deleteTable(interp->prog.lables);
    free(interp->prog.operations);
    free(interp->state.memory);

    return 0;
}

