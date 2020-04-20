#include<stdio.h>

struct Person {
    char name[200];
    int age;
};


void test1() {
    struct Person p1 = {"jack",18};
    struct Person p2 = {"july",20};

    printf("%s,%d\n",p1.name,p1.age);
    printf("%s,%d\n",p2.name,p2.age);

    p1 = p2;
    printf("%s,%d\n",p1.name,p1.age);

    return 0;
}


int main() {

    test1();
    
}
