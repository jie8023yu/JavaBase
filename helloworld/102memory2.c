#include<stdio.h>
//calloc

/*
分配多少长度为size字节的连续区域,同时初始化为0
成功，返回分配空间的初始地址，失败，返回NULL
*/
void test01() {
    //通过malloc分配没有初始值，需要通过memset来初始值
    int *pp = malloc(sizeof(int) * 5);
    for (int i = 0; i < 5; i++) {
        printf("%d,",pp[i]);
    }
    printf("\nmemset----------------->\n");
    memset(pp,0,20);
    for (int i = 0; i < 5; i++) {
        printf("%d,",pp[i]);
    }
    printf("\ncalloc--------------->\n");
     
    int *p = calloc(5,sizeof(int));
    for (int i = 0; i < 5; i++) {
        printf("%d,",p[i]);
    }
    printf("\n");
    //赋值
    for (int i = 0; i < 5; i++) {
        p[i] = 10;
    }
    for (int i = 0; i < 5; i++) {
        printf("%d,",p[i]);
    }
   
}
 /*realloc重新分配malloc和calloc在堆中分配内存的大小

    64 -》 128 如果后面有连续的空间，就接着分配
    如果没有连续的空间，就会新找一块连续的空间，分配，进行旧值拷贝
    */
void test02() {

    int *p = malloc(sizeof(int));
    printf("%p\n",p);
    int *p2 = malloc(sizeof(int));
    printf("%p\n",p2);
}

int main() {
    // test01();
    test02();
    return 0;
}