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


void 

test5() {
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
    printf("------------------------------------\n");
    char* c1[] = {"aaa","bb","ccc"};
    /*
    二级指针
    将一级指针的地址赋值给了二级指针
    cll[0]其实就是访问第一个地址中的值，----》"aaa"的首地址

    */
    printf("c1=%p,",&c1);
    pp();
    for (int i = 0; i < 3; i++) {
        printf("c1[%d]=%p,address is %p\n",i,c1[i],&c1[i]);
    }
    char** c11 = &c1;
    printf("c11=%p,address is %p\n",c11,&c11);

    //注意：二级指针，*c11就是获取一级指针上的值，还可以通过c11[i]的方式获取
    printf("c11=%p\n",*c11);

    for (int i = 0; i < 3; i++) {
        //如何一次就取出aaa的内容
       printf("c11[%d]=%p,address is %p\n",i,c11[i],&c11[i]);
    }
    printf("---------------------------------------\n");
    int i1 = 1;
    int i2 = 222;

    int* iArr[] = {&i1,&i2};
    int** iArr2 = &iArr;

    printf("%p,%p\n",&i1,&i2);
    for (int i = 0; i < 2; i++) {
        printf("iArr[%d]=%p,address is %p,value is :%d\n",i,iArr[i],&iArr[i],*iArr[i]);
    }
    printf("%p,%p\n",iArr2,&iArr2);

    for (int i = 0; i < 2; i++){
       printf("iArr2[%d]=%p,address is %p,value is :%d\n",i,iArr2[i],&iArr2[i],*iArr2[i]);
    }
    for (int i = 0; i < 2; i++){
       printf("*(iArr2+[%d])=%p,address is %p,value is :%d\n",i,*(iArr2+i),iArr2+i,**(iArr2+i));
    }

    printf("---------------------------------------\n");
    char a = 'a';
    char b = 'b';
    char c = 'c';
    char * c2Arr[] = {&a,&b,&c};

    printf("&a=%p\n",&a);
    printf("&b=%p\n",&b);
    printf("&c=%p\n",&c);
     for (int i = 0; i < 3; i++) {
        printf("c2Arr[%d]=%c,address is %p\n",i,*c2Arr[i],&c2Arr[i]);
    }

    // char *cArr[] = {'a','b','c'};
    char *cArr[] = {"a","b","c"};
    cArr[2] = "d";
    for (int i = 0; i < 3; i++) {
        printf("cArr[%d]=%p,address is %p\n",i,cArr[i],&cArr[i]);
    }
    char** cArr2 = &cArr;
    printf("cArr is:%p,&cArr address is %p,*cArr is : %c\n",cArr,&cArr,*cArr);
    printf("cArr2=%p,address is %p\n",cArr2,&cArr2);
    printf("*(&cArr2)=%p\n",*(&cArr2));
    printf("*cArr2=%p\n",*cArr2);

    for (int i = 0; i < 3; i++) {
        printf("cArr[%d]=%c,address is %p\n",i,**(cArr2 + i),&(**(cArr2 + 1)));
    }

    int i = 3;
    int* pi = &i;
    printf("i=%d\n",i);
    printf("*pi=%d\n",*pi);
    printf("pi=%d\n",pi);



}

void test5_1() {
    char *s1 = "abc";
    char *s2 = "bca";

    // *s1 = "c";
    printf("*s1=%c,s1=%s,&(*s1)=%p",*s1,s1,&(*s1));
    pp();
    printf("*s2=%c,s2=%s,&(*s2)=%p",*s2,s2,&(*s2));
    pp();
    char *cArr[] = {s1,s2};
    // printf("%p\n",cArr);
    // cArr++;p
    // printf("%p\n",cArr);
    for (int i = 0; i < 2; i++) {
        printf("%p\n",cArr[i]);
    }

    char *cArr2[] = {"abc","b"};

    printf("&(abc)=%p\n",&("abc"));

    for (int i = 0; i < 2; i++) {
        printf("cArr2[%d]=%p,%s\n",i,cArr2[i],cArr2[i]);
    }

    char *temp = cArr2[0];
    printf("temp = %p,&temp=%p",temp,&temp);
    for (int i = 0; i < 3; i++) {
        //temp[i] == *(temp++)
        printf("%c",*temp++);
    }
    pp();
    printf("--------------------\n");
    




    char *a = 'a';
    char *b = 'b';
    char c = 'c';
    printf("a=%p\n",a);
    printf("&a=%p\n",&a);
    printf("*(&a)=%p\n",*(&a));
    printf("%c,%p,%p\n",c,c,&c);
    printf("-------------------------->\n");
    char *c2[] = {a,b};
    printf("c2 address is %p\n",&c2);
    for (int i = 0; i < 2; i++) {
        printf("%p,%p\n",c2[i],c2+i);
    }
    printf("\n-------------------------->\n");

    char *pc = &c;

    printf("*pc=%c\n",*pc);

    printf("*a=%c,a=%p,&(*a)=%p",*a,a,&(*a));
    pp();
    printf("*b=%c,b=%p,&(*b)=%p",*b,b,&(*b));

}

void test5_2() {

    /*这样写，相当于直接给指针赋值了，a就相当于97，去找地址97了，这样写是不行的 
        char *c1 = 'a'; ===  char *c11= 97;
        这样就相当于野指针操作了，是不行的
    */
   /*  char *c1 = 'a';
    char *c11= 97;
    char *c2 = 'b';
    // *c1 = 'd';
    // printf("c1=%p,&c1=%p,*c1=%d\n",c1,&c1,*c1);     
    printf("c2=%p,&c2=%p\n",c2,&c2);

    printf("c11=%p,&c11=%p\n",c11,&c11); */
    /* char c = 'c';
    char *c3 = &c;
    printf("c3=%p,&c3=%p,*c3=%c\n",c3,&c3,*c3); */

    char a = 'a';
    char b= 'b';
    char* pcArr2[] = {&a,&b};
    printf("%p,%p\n",&a,&b);

    printf("%p\n",&pcArr2);

    //第一种写法
    for (int i = 0; i < 2; i++) {
       printf("%p,%p,%c\n",pcArr2[i],&pcArr2[i],*pcArr2[i]);
    }
    //第二种写法, pcArr2就是指针数组的首地址, pcArr2[i] === *(pcArr2 + i)  *pcArr2[i] === **(pcArr2 + i)
    for (int i = 0; i < 2; i++) {
       printf("%p,%p,%c\n",pcArr2 + i,*(pcArr2 + i),**(pcArr2 + i));
    }
    printf("----------------------------------\n");
    //这样写，是不是还是直接操作地址，野指针了，对不对，写的是不是有问题
   /*  char* pcArr[] = {'a','b','c'};
    int len = sizeof(pcArr);
    printf("%d\n",len);
    for (int i = 0; i < 3; i++) {
      printf("%p,%p\n",pcArr[i],pcArr + i);
    } */

    char *pcArr3[] = {"a","b","c"};
    for (int i = 0; i < 3; i++){
         printf("%p,%p,%s\n",pcArr3[i],pcArr3 + i,pcArr3[i]);
    }

    printf("%p\n",pcArr3[2]);
    printf("%c\n",*pcArr3[2]);
    printf("%s\n","test");
    char t = "abc";
    printf("%c\n",t);

    char *pc = &t;
    printf("%c,",*pc++);
    printf("%c,",*pc++);
    printf("%c,",*pc);
    pp();
    char t2[] = "abc";
    printf("%s\n",t2);


            






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
    printf("pointer array---------------->\n");
    int a = 10;
    int b = 20;
    int c = 30;

    int* pInt[] = {&a,&b,&c};
    printf("%p,value=%d\n",pInt[0],*pInt[0]);

    char str[4] = "tes";

    printf("%s\n",str);


    printf("------------------------------------\n");

    char* arr[] = {"aaa","bbbbb","cccc"};
    // for (int i = 0; i < 3; i++) {
    //     // printf("%s\n",arr[i]);
    //     printf("%s\n",*(arr + i));
    // }
    // char** dArr = &arr;

    // printf("arr address is:%p\n",&arr);

    printf("arr[0] address is:%p,value:%s\n",&arr[0],*(&arr[0]));
    printf("arr[1] address is:%p,value:%s\n",&arr[1],*(&arr[1]));
    printf("arr[2] address is:%p,value:%s\n",&arr[2],*(&arr[2]));
    printf("address = %p\n",arr[0]);
    printf("address = %s\n",arr[0]);
    printf("address = %c\n",*arr[0]);
    

    printf("arr address is:%p,value:%p\n",&arr,arr);
    printf("arr+1 address is:%p,value:%p\n",&arr+1,arr+1);
    printf("arr+2 address is:%p,value:%p\n",&arr+2,arr+2);

    // printf("dArr address is:%p\n",&dArr);
    // printf("dArr[0] address is:%p\n",&dArr[0]);
    // printf("dArr[1] address is:%p\n",&dArr[1]);
    // printf("dArr[2] address is:%p\n",&dArr[2]);
    // printf("dArr=%p\n",dArr);
    // printf("dArr1=%s\n",*dArr);
    // // printf("dArr11=%p\n",*(*dArr));
    // printf("dArr2=%p\n",&dArr);
    // printf("dArr3=%p\n",*(&dArr));


    printf("address pointer----------------------------<<<<<</n");

    int* arr2[] = {1,2,3,4};
    for (int i = 0; i < 4; i++) {
        printf("%d\n",arr2[i]);
    }


    // char* ss[] = {"abc"};
    // char ss[] = "abc";
    char ss[] = {'a','b','c','\0','d'};
    //\0字符，就是一个空格
    printf("%s\n",ss);

    printf("%s\n",ss);

    printf("%s\n",*arr);

}

/**
 * 指针与字符串
 **/
void test8() {
    printf("pinter and array------------------------------------->start\n");
    char arr[] = "hello world";
    char* p;
    p = arr;
    // printf("%s\n",*p);
    printf("%p\n",arr);
    printf("*p=%d\n",*p);
    p = 101;
    // printf("%c\n",p);
    char** pp = &p;
    printf("%s\n",pp[0]);

    /* for (int i = 0; i < 12; i++) {
        printf("value:%d\n",*p++);
    } */
    // printf("\n");
    printf("%s\n",arr);
    printf("%p\n",&arr);
    printf("%d\n",p);
    printf("%p\n",&p);
    *p = 'A';
    p++;
    *p = 'B';
    printf("%s\n",arr);
    printf("pinter and array------------------------------------->end\n");
}

void test8_1() {
    // char arr[] = {"aaa","bbbb","cccc"};

    //字符数组
    char c[] = "aa";
    char c2[] = {'a','b','c'};
    // pirntf("%s\n",arr[0]);
    printf("%s\n",c);
    printf("%s\n",c2);

    //数组，c就是数组原数的首地址

    //拿C的地址去找ASCII对应的值，找不到就是?
    printf("%c\n",c);
}


void test9() {
    int arr[] = {25701,100,3};
    int* p;
    p = arr;
    printf("%p\n",arr);
    //%s  将这个值转换为ASCII来显示，int 会读到4个char
    printf("%s\n",arr);
    printf("arr=%d\n",*arr);

    printf("%p\n",p);
    printf("%d\n",p);
    //int 4个字节， char 1个字节

    //字符串
    char arr2[] = "he\0ll0";
    char arr21[] = "hello";
    int arrI[1] = {25701};
    int arrI2[1] = {25701};
    char arr31[] = {'h','e','l','l','o'};

    int len = sizeof(arr2);
    printf("arrI length:%d\n",sizeof(arrI));
    printf("arr2 length:%d\n",len);
    
    char* c1 = arrI;
    for (int i = 0; i < 9; i++) {
        printf("%p,%c\n",c1,*c1);
        c1++;
    }

    for (int i = 0; i < len; i++) {
        //\0读出来就是一个空格
        printf("%c,",arr2[i]);
    }
    printf("\n");

    printf("%p\n",arr2);
    printf("%p\n",arr21);
    printf("%p\n",arrI);
    printf("%p\n",arrI2);
    printf("%p\n",arr31);
    printf("%s\n",arr2);
    printf("%s\n",arr21);
    //会一直往读，直到读到一个\0字符
    printf("%s\n",arr31);

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
    /* printf("pointer not init\n");
    printf("%p\n",p1);
    printf("%p\n",p2);
    printf("%p\n",p3);   */
    //操作没有初始化的指针(这里实际就相当于操作一个野指针)，所以使用指针一定要初始化
    // *p1 = 10;   //这样操作可能就会有问题


    // test2();
    // test3();

    char * arr[] = {
        "aaaa","bbbb","cccc"
    };
    int size = sizeof(arr) / sizeof(arr[0]);
    // test4(&arr,size);

    // test6();


    // test7();

    // test8();
    // test8_1();

    // test9();
    //上面的都是为了验证一下test5中的内容
    // test5();
    // test5_1();
    test5_2();


    return 0;
}