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
                初始化的全局变量
                初始化的静态全局变量
                初始化的静态局部变量
            未初始化的数据
                未初始化的局部变量
                未初始化的静态局部变量
                未初始化的全局变量
                未初始化的静态全局变量
            字符串常量
            #define定义的常量存放在数据区
        栈区:
            不同操作系统中为系统中每一个程序的栈空间分配的空间大小不等，
            一般Windows是1-8M不等
            一般Linux是1-16M不等

            常量在栈区（C语言），C++存在数据区
        堆区：
            程序创建大的数据，应该放在堆区

 **/
int gi1;
int gi2 = 10;
static int sgi1;
static int sgi2 = 20;

void test4() {
    //未初始化的局部变量啊
    int i1;
    static int si1;

    int i2 = 2;

    static int si2 = 3;

    printf("&i1=%p\n",&i1);
    printf("&si1=%p\n",&si1);
    printf("&i2=%p\n",&i2);
    printf("&si2=%p\n",&si2);

    printf("&gi1=%p\n",&gi1);
    printf("&gi2=%p\n",&gi2);
    printf("&sgi1=%p\n",&sgi1);
    printf("&sgi2=%p\n",&sgi2);

    //申请数组
    // int arr[100000000];  //这么大的栈已经申请不下来了  1亿长度的
    // int arr[10000000];  //这么大也不行 1千万
    printf("start\n");
    // int arr[1000000];
    int arr[100000];  //这么大申请下来了
    printf("end\n");

    //开辟堆空间
    int *p = (int*)malloc(1000000000 * sizeof(int)); //大概3G的空间
    for (int i = 0; i < 1000000000; i++) {
        *p = 10;
        p++;
    }
    printf("%p\n",p);
    // system("pause");
}



/**
 * 数据存储范围
 */
int l;
int f = 10;
static int g;
static int h = 10;
void test5() {
    int a = 10;
    static int c;
    static int b = 20;
    int i[10];
    int j[10] = {0};

    int *k;
    int *kk = &a;
    char *p = "hello world";
    char ch[] = "hello world";
    printf("&b=%p\n",&b);
     printf("&k=%p\n",&k);
    printf("end");
}


/**
 *堆空间操作
 * */
void test6() {
    int * p = malloc(10 * sizeof(int));
    for (int i = 0; i < 10; i++) {
        p[i] = rand()  % 50;
    }
    for (int i = 0; i < 10; i++) {
        printf("%d,",p[i]);
    }
    printf("\n");
    //冒泡排序
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10 - i - 1; j++) {
            if (p[j] > p[j+1]) {
                int temp = p[j];
                p[j] = p[j+1];
                p[j+1] = temp;
            }
        }
    }
    for (int i = 0; i < 10; i++) {
        printf("%d,",p[i]);
    }

    free(p);

}

/**
 * 堆空间操作字符串
 **/
void  test7() {
    char * p = malloc(sizeof(char));
    // printf("%s\n",p);  //如果没有赋值，打印出一些奇怪的东西

    //内存空间，拷贝内容
    strcpy(p,"hello worldtest");
    printf("%p\n",p);  
    for (int i = 0; i < 11; i++) {
        printf("address = %p,value = %c\n",p,*p++);
    }
    // printf("%s\n",p);  
    printf("%d\n",sizeof(char));
    char * p2 = malloc(sizeof(char));
    char * p3 = malloc(sizeof(char));
    printf("%p\n",p2);
    printf("%p\n",p3);

    //上面的看起来很奇怪

    printf("-------------------------------------\n");
    //下面这两个是不是都在字符串常量区，地址是一样的（不可以修改）
    char * pp = "hello world";
    char * pp2 = "hello world";  
    *pp = 'A';
    printf("%p\n",pp);
    printf("%p\n",pp2);
    //堆区(可以修改)
    char * pp3 = malloc(sizeof(char) * 12);
    strcpy(pp3,"hello world");
    printf("%p\n",pp3);
    *pp3 = 'A';
    printf("%s\n",pp3);
    //栈区（可以修改）
    char arr[] = "hello world";
    printf("%p\n",arr);

}

/**
 * 内存操作函数
 **/
void test8() {
    int *p = malloc(sizeof(int) * 10);


    //内存操作函数，操作地址，设置的值（字符，0-255中的字符），一共设置多少字节
    memset(p,0,40);

    for (int i = 0; i < 10; i++) {
        printf("%d,",p[i]);
    }
    printf("\n");

    char *p2 = malloc(sizeof(char) * 10);
    memset(p2,65,10);
    printf("%s,",p2);
     printf("\n");

    //可以重置内存的值，栈区也是可以的
    int arr[5] = {1,2,3,4,5};
    memset(arr,0,20);
    for (int i = 0; i < 5; i++) {
        printf("%d,",arr[i]);
    }
    printf("\n");

    printf("----------------------memcpy\n");
    //memcpy   desc,src,lengh
    int arr2[5] = {1,2,3,4,5};
    int *p3 = malloc(sizeof(int) * 5);
    memcpy(p3,arr2,20);  //指定字节
    for (int i = 0; i < 5; i++) {
        printf("%d,",p3[i]);
    }
    printf("\n");

    //拷贝
    int arr3[5] = {1,2,3,4,5};
    memcpy(&arr3[2],arr3,8); //拷贝内存重叠 (最好不要内存重叠，可能会出问题，同一块内存又在读，又在写)
    for (int i = 0; i < 5; i++) {
        printf("%d,",arr3[i]);
    }

    /**
     * strcpy
     * memcpy
     * 
     * 函数参数不同
     * strcpy拷贝字符串，memcpy可以拷贝一块内存
     * 拷贝结束标志不一样，strcpy读\0结束，memcpy读到指定字节
     * 
     */

    printf("\nmemmove()---------------------------------\n");
    /*
    memmove() 功能和memcpy一样，区别在于：dest和src所指的内存空间重叠时，memmove()扔能处理，不过执行效率比memcpy低些，
    原因是它需要开辟出一块临时的空间来存放值，额外空间+额外赋值操作

    我这可以随意操作内存
    */
   int arr4[5] = {1,2,3,4,5};
   memmove(&arr4[2],arr4,16);
   for (int i = 0; i < 6; i++) {
        printf("%d,",arr4[i]);
    }
    printf("\nmemcmp()---------------------------------\n");
    //memcmp 比较两块内存地址的内容是否相同，前多少个地址
    int a[5] = {1,2,3,4,5};
    int a2[3] = {1,2,3};
    printf("%d\n",memcmp(a,a2,16));

    int *ppp1 = malloc(sizeof(int) * 5);
    char *ppp2 = malloc(sizeof(char) * 20);
    memcpy(ppp1,"hello",6);
    memcpy(ppp2,"hello",6);

    printf("%d\n",memcmp(ppp1,ppp2,6));

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

    // test2();

    // test3();

    // test4();

    // test5();

    // test6();

    // test7();

    test8();
    return 0;

}