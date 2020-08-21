#include<stdio.h>
#include<string.h>
#include <stdlib.h>

#define ZIP_END 255

struct Person {

    char name[50];

    int age;
};


typedef struct Person P;


int main() {

    /* P p = {"Jack",18};
    P p1 = p;
    printf("%p,%p\n",p,p1);
    printf("%s-%p,%d-%p\n",p.name,p.name,p.age,&p.age);
    printf("%s-%p,%d-%p\n",p1.name,p1.name,p1.age,&p1.age);


    printf("%d\n",sizeof(P));
    P *pp = malloc(sizeof(P));
    strcpy(pp->name,"July");
    pp->age = 20;
    printf("%p\n",pp);
    char* cp = pp;
    for (int i = 0; i < 56;i++) {
        if (i == 55) {
            printf("%p\n",&cp[i]);
        } 
        printf("%c,",cp[i]);
        // cp++;
        
    }
    printf("\n");
    printf("%s-%p,%d-%p\n",(*pp).name,pp->name,pp->age,&(pp->age)); */


    printf("------------------------------\n");

    unsigned char * pt1 = malloc(sizeof(char) * 3);
    int * pt2 = malloc(sizeof(int) * 3);
    pt1[0] = 255;
    pt1[1] = 5;
    pt1[2] = 5;
    // printf("%c\n",pt1[0]);

    //0xff  ----> 8位 -》2^8 - 1 = 255  1字节

    if (pt1[0] == ZIP_END) {
        printf("equals--------\n");
    } else {
        printf("not equals--------\n");
    }


}