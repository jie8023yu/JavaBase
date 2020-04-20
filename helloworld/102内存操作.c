#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//引入头文件
#include "102_2.h"

/**
 * 内存管理
 **/
/**
 *
 * 作用域
 **/
// extern int a;

//全局变量，作用范围是整个项目，只是需要声明extern , 我测试就是不行
void test1() {
    //变量的作用范围,从创建到结束
    int aa = 10;
    //此时打印A会报错,a定义在102_1.c文件中，如果想使用，使用extern关键字,我发现我这样声明都没用
    // printf("%d\n",a);  
}



void tt2() {
    static int aa = 20;
    printf("%d\n",aa++);
}

// extern void func();  //声明func函数 ，一般，声明会写在一个头文件中(比如将这个声明写在102_2.h头文件中)

/**
 * 静态变量
 **/
void  test2() {
    //局部变量
    int bb = 10;
    //静态局部变量
    static int aa = 20;
    printf("%d\n",aa);

    tt2();
    func();
}
//如果写在这，test2想调用，编译会把报错，找不到func函数，此时可以把函数定义在上面，还可以在test2声明这个函数
void func() {
    printf("hello world\n");
}

// extern void func2();
// void test3() {
//     func2();
// }

/**
 * 内存布局
 * cmd下进入到exe的目录下，使用size命令
 * size 命令
 * size 102内存操作.exe 
 * 会显示如下内容
 * text    data     bss     dec     hex filename
   9980    2272    2432   14684    395c 102内存操作.exe

   text -》代码区
   data-》静态数据/全局初始化数据区
   bss->未初始化初始化数据区
   dec-》十进制总和
   hex ->十六进制总和
   filename ->文件名称


   内存区域：
        代码区：
            程序指令
        数据区（静态区，全局区）：
            初始化的数据
            未初始化的数据

 **/
void test4() {

}



int main() {

    /* char * p = malloc(sizeof(char) * 10);
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
    } */

    test2();

    // test3();
    return 0;

}