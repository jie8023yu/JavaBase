#include<stdio.h>
#include<stddef.h>
#include<stdlib.h>
struct person {
    char name[10];
    int age;
    char sex;
};

//指针步长, 向后跳多少字节
void test2() {
    printf("pointer step length------------------->\n");
    //NULL其实指向的就是0
    char *p = NULL;
    //取地址%p
    printf("%p\n",p);
    printf("%p\n",p + 1);
    struct person per = {"Julytest",10,'1'};
    printf("name:%d\n",offsetof(struct person,name));
    printf("age:%d\n",offsetof(struct person,age));
    printf("sex:%d\n",offsetof(struct person,sex));
    //取字符%c
    //取数字%d
    // printf("%d\n",* (int *)((char *) &per + offsetof(struct person,age)));


}


void pp() {
   printf("\n"); 
}

void changeVal(int *val,int value) {
    *val = value;
}

//指针间接赋值
void test3() {
    int c = 100;
    changeVal(&c,10);
    printf("c=%d\n",c);
}

void test4(char **arr,int len) {
    //这里写arr[i] 相当于 *(arr + i)
    for (int i = 0; i < len; i++) {
        printf("%s\n",arr[i]);
    }
}


void test5() {
    printf("pointer and array-------------->\n");
    int arr[10] = {0};
    //arr就是数组的首地址，跟&arr[0]的地址相同
    printf("%p\n",arr);
    printf("%p\n",&arr[0]);

    //赋值首地址给指针
    int* p = arr;
    for (int i = 0; i < 10; i++) {
        printf("%d,",*p);
        p++; //指针移动
    }
    p = arr;
    printf("\n");
    for (int i = 0; i < 10; i++) {
        printf("%d,",*(p + i));
    }
    pp();
    char* c1[] = {"aaa","bbbddd","ccc"};
    char** c11 = &c1;
    for (int i = 0; i < 3; i++) {
        //如何一次就取出aaa的内容
        printf("%s-",c11[i]);
    }

}
/**
 * 多级指针
 * */
void test6() {
    pp();
    int a = 10;
    int* p = &a;
    int** pp = &p;
    //*pp = &a  **pp=a
    printf("pp=%p\n",pp);
    printf("p=%p\n",p);
    printf("*pp=%p\n",*pp);
    printf("**pp=%p\n",**pp);
}

/**
 * 指针数组
 **/
void test7() {
    int a = 10;
    int b = 20;
    int c = 30;

    int* pInt[] = {&a,&b,&c};
    printf("%p,value=%d\n",pInt[0],*pInt[0]);

    char str[4] = "test";

    printf("%c",str);
}




int main() {

    /**
     * 
     * 指针问题
     * 指针是一种数据类型
     * 指针在32位系统中都是4个字节
     * 在64位系统都是8个字节
     * 
     **/
    //指针没有初始化的时候，会随机指向一个地址
    int * p1;
    int * p2;
    int * p3;
    printf("pointer not init\n");
    printf("%p\n",p1);
    printf("%p\n",p2);
    printf("%p\n",p3);  
    //操作没有初始化的指针(这里实际就相当于操作一个野指针)，所以使用指针一定要初始化
    // *p1 = 10;   //这样操作可能就会有问题


    // test2();
    // test3();

    char * arr[] = {
        "aaaa","bbbb","cccc"
    };
    int size = sizeof(arr) / sizeof(arr[0]);
    // test4(&arr,size);

    // test5();
    // test6();


    test7();

    return 0;
}