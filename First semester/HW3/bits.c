#include <stdio.h>
#include <stdlib.h>
int bitAnd(int x, int y) {
    return ~(~x | ~y);
}
int bitXor(int x, int y){
    return ~ (x & y) & ~ (~ x & ~ y);
}
int thirdBits(void) {
    int b0 = 36;
    int b1 = 73;
    int b2 = 146;
    int b3 = 36;
    return (b3 << 24) | (b2 << 16) | (b1 << 8) | b0;
}
int fitsBits(int x, int n) {
    return !(((~x & (x >> 31)) + (x & ~(x >> 31))) >> (n + ~0));
}
int sign(int x) {
    return (!!x) | (x >> 31);
}
int getByte(int x, int n){
    return (x >> (n << 3)) & 0xff;
}
int logicalShift(int x, int n){
    int msk = ~ ((~ (1 << n)) << (32 - (!n & 1) + (~ n + 1)));
    return (x >> n) & (msk);
}
int bang(int x) {
    return ((~((~x + 1)) & ~x) >> 31 ) & 1;
}
int isPower2(int x){
    return !((!x) | (x & (x + ~(x >> 31))));
}
int conditional(int x, int y, int z){
    return ((!x + ~0) & y ) | (~(!x + ~0) & z);
}
int addOK(int x, int y) {
    int mask = 1 << 31;
    int mX = mask & x;
    int mY = mask & y;
    int mSum = mask & (x + y);
    int p1 = !(mX ^ mY);
    int p2 = !(mX ^ mSum);
    return (!p1) | p2;
}


