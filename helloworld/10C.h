#pragma once   //防止重复编译

#include<stdio.h>

//定义方法
// void show();


/* #ifdef __cplusplus;  //两个下划线
extern "C" {
#endif; */
void show();
/* #ifdef __cplusplus;
}
#endif; */

void show() {
    printf("hello world");
}
