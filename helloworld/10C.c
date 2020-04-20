#include<stdio.h>
#include <stdlib.h>



//1.全局变量检测增强(可以编译通过，正常执行)
int a;
int a = 10;
//2.函数检测增强(编译只会有警告，正常执行) C++也是可以的，只是参数必须指定类型
int get(w,h) {
    
}
//函数调用参数检测(正常编译，有警告)
void test() {
    get(1,2,3);
}

//3.类型转换检测增强
void t2() {
    char *p = malloc(sizeof(64));  //malloc的返回值是Void*

}
//4.结构体增强
struct Person {
    int age;

    //想写一个方法(不支持)
    /* void plusAge() {

    } */
};
//5.C语言中必须写struct
void t3() {
    //C语言中必须写struct
    struct Person p1;
}
//6.bool类型增强 C语言中没哟bool类型
// bool flag;

//7.三目运算符增强
void t4() {
    int a = 20;
    int b = 30;
    printf("ret = %d \n", a > b ? a : b);
    //不支持下面的写法
    // a > b ? a : b = 100;
}

//8.const增强
const int m_a = 10; //受到保护，不能修改
void t5() {
    //不能这样写
    // m_a = 10;

    const int m_b = 20;  //伪常量
    //不能这样写去修改
    // m_b = 30;
    //用指针修改(可以用)
    int *p = &m_b;
    *p = 200;
    printf("*p=%d,m_b=%d\n",*p,m_b);

}

//9.c语言中const默认是外部连接
void t6() {
    // extern const int cc; //告诉编译器去外部文件中找找这个CC常量
    // printf("cc=%d",cc);
}


//c语言中的封装
struct Person2 {
    char name[64];
    int age;
};

void eat(struct Person2 *p) {
    printf("%s eat person \n",p -> name);
}

struct Dog{
    char name[30];
    int age;
};
void eatDog(struct Dog *d) {
    printf("%s eat dog\n",d -> name);
}



int main() {
    // extern const int cc; //告诉编译器去外部文件中找找这个CC常量
    // printf("cc=%d",cc);  //貌似也是不支持的啊

    printf("hello world!");
    t4();
    t5();
    t6();

    struct Person2 p1 = {"test",12};
    eat(&p1);
    struct Dog d = {"d1",2};
    eatDog(&d);
    eatDog(&p1);

    return 0;
}