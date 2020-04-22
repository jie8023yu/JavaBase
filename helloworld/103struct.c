#include<stdio.h>
#include<string.h>
/**
 * 结构体
 * 
 */
struct Person {
    char name[200];
    int age;
} per;  //可以在这里加一个per,结构体变量，下面可以直接赋值

typedef struct Person P;
/**
 * 
 * 赋值方式
 * */
void test1() {
    //1.第一种赋值方式
    struct Person p1 = {"jack",18};
    printf("%s,%d\n",p1.name,p1.age);
    //第二种赋值方式
    struct Person p2;
    // p2.name = "july";  //不能这样赋值
    strcpy(p2.name,"july");  //必须这样赋值
    p2.age = 20;
    printf("%s,%d\n",p2.name,p2.age);
    //第三种赋值方式
    per.age = 21;
    strcpy(per.name,"jane");
    printf("%s,%d\n",per.name,per.age);


    return 0;
}

void test2() {
    //结构体数组
    struct Person arr[] = {{"test",10},{"test2",20}};
}


void test3() {
    struct Person * pp = malloc(sizeof(struct Person));
    strcpy(pp->name,"test");
    pp->age = 10;
    printf("%s,%d",pp->name,pp->age);
}

void test4() {
    P pp1 = {"test",20};
    P pp2 = pp1;  //一个一个字节的拷贝

    P* p = malloc(sizeof(P));
    strcpy(p->name,"jack");
    p->age = 20;
    P* p2 = p;
    printf("%p,%p\n",p,p2);
    printf("%s-%p,%s-%p",p->name,p->name,p2->name,p2->name);
}
struct Teacher {
    char * name;
    int age;
};
typedef struct Teacher T;
void test5() {
    T t1;
    t1.name = malloc(sizeof(char) * 200);
    memset(t1.name,0,200);
    strcpy(t1.name,"aaaaaa");
    t1.age = 10;
    T t2;
    t2.name = malloc(sizeof(char) * 200);
    memset(t2.name,0,200);
    strcpy(t2.name,"bbbbbbbbbbbbb");
    t1.age = 20;
    // t1 = t2; //赋值就是字节数据的拷贝
    //内部有指针指向堆中内存，就不要使用系统的赋值
    if (t1.name != NULL) {
        free(t1.name);
        t1.name = NULL;
    }
    t1.name = malloc(strlen(t2.name) + 1);
    strcpy(t1.name,t2.name);
    t1.age = t2.age;
    printf("%s-%p,%s-%p\n",t1.name,t1.name,t2.name,t2.name);
    printf("%d,%d\n",t1.age,t2.age);

    if (t1.name != NULL) {
        free(t1.name);
        t1.name = NULL;
    }
    if (t2.name != NULL) {
        free(t2.name);
        t2.name = NULL;
    }
}



int main() {

    // test1();

    // test3();

    // test4();
    test5();
    return 0;
    
}
