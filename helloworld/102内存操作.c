#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int main() {

    char * p = malloc(sizeof(char) * 10);
    strcpy(p,"hello");
    printf("%s\n",p);
    free(p);
    
    printf("%s\n","memory operate function-------------------------");
    //内存操作函数
    int * p2 = (int *)malloc(sizeof(int) * 10);
    //堆空间
    memset(p2,1,40);
    for (int i = 0 ; i < 10 ; i++) {
        printf("%d\n",p2[i]);
    }
    //栈空间
    printf("%s\n","reset stack memory");
    int arr[10] = {1,2,3,4,5,6,7,8,9,10};
    memset(arr,0,40);
    for (int i = 0; i < 10; i++) {
        printf("%d\n",arr[i]);
    }
    free(p2);


    //memcpy
    int arr2[10] = {1,2,3,4,5,6,7,8,9,10};
    printf("%s\n","memcpy-------------------->");
    int * p3 = malloc(sizeof(int) * 10);
    memcpy(p3,arr2,20);
    for (int i = 0; i < 10; i++) {
        printf("%d\n",p3[i]);
    }


    return 0;

}