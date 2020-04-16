#include<stdio.h>

int main() {

    int i = 1;
    int b = 2;

    int a[3] = {1,2,3};

    int * p = &b;
    // int * p2 = &b;
    int * p3 = &i;

    printf("%p",p);

    int * a1;
    int * a2;
    int * a3 = &i;
    printf("\n");
    printf("p:%d\n",sizeof(p));
    printf("int*:%d\n",sizeof(int *));
    return 0;
}
