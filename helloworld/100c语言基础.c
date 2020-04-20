#include<stdio.h>


/**
 * 数据类型----》
 *  基本类型
 *      整型：int，short，long, long long (长长整型)
 *      字符型：char
 *      实型（浮点型）：
 *          float（单精度型）
 *          double（双精度型）
 *  构造类型
 *      数组类型
 *      结构类型 struct
 *      联合类型 union
 *      枚举类型 enum
 *    
 *  指针类型
 *      char*,int*,int**等
 * 
 * 常量：
 *  宏定义的方式 #define
 *  const定义的方式，这种方式定义的常量可以通过指针来改变
 * 
 * 十进制数
 * 正常  %d  有符号的
 * 8进制数
 * 0开头 %o
 * 16进制数
 * 0x开头  %x  字母小写输出  %X 字母大写输出
 * 
 * 
 * %u 无符号的十进制数
 * 
 * 
 * 
 * int a;
 * 输入流  scanf("%d",&a);
 * 
 * c
 * getchar();
 * 
 * %c字符
 * 
 * 
 * 字符串常量：
 * 'a'为字符常量
 * "a"为字符串常量，除了a本身，后面还有一个\0表示结束标志位
 * 
 * 
 * 字符数组
 * 
 * 
 * 
 **/
void 

test1() {


    //存储一个字符，单引号
    char c = 'a';
    char c2 = 'b';
    printf("%c\n",c);
    printf("%c\n",c2);

    char c3;
    //接收输入
    // scanf("%c",&c3);
    //接收字符的第二种方式开
    // c3 = getchar();
    printf("%c\n",c3);

    //字符数组
    // char cArr[6] = {'h','e','l','l','o'};
    // char cArr[] = "hello";

    //这样的使用%s输出，可能会出现乱码，他需要一直找到\0的位置
    char cArr[] = {'h','e','l','l','o'};
    printf("%d\n",sizeof(cArr));
    //%s 会读取 /0之前的所有字符数据
    printf("%s\n",cArr);
    printf("-----");
    printf("\n");
    for (int i = 0; i < 10; i++) {
        printf("%c,",cArr[i]);
    }
    printf("\n");

    //字符串输入
    char str[200];
    //注意：空格就相当于\0，这个就算字符串的结束标志符了
    // scanf("%s",&str);
    // printf("%s\n",str);
    //这个就可以解决
    scanf("%[^\n]",&str);
    printf("%s\n",str);


}
/**
 * 原码
 *   最高位符号位，0正数，1负数
 * 补码
 *    
 * 反码
 * 
 * 
 * 正数的原码，反码，补码都一样
 * 负数的反码，符号位不变，其它位取反
 * 负数的补码，反码+1
 * 
 * 
 * 数据在计算机都是以补码的方式存储的
 * 
 * 主要原因：
 *  统一了0的编码
 *  将符号位和其它位统一处理
 *  将减法运算转变为加法运算
 *  两个用补码表示的数相加，如果最高位有进位，则进位被舍弃
 * 
 * 溢出：
 * 在数据操作的时候会导致超出数据类型大小，会向前位进1操作，多于原始数据类型大小，会被系统自动舍弃，保留从后面开始数据类型大小的位数
 * 
 * sizeof关键字，计算一个类型的大小（字节）
 * 
 **/
void test2() {
    int a = 10;
    printf("sizeof------------------------------>\n");
    printf("%d\n",sizeof(int));
    printf("%d\n",sizeof(a));

}


int main()  {

    test1();
    // test2();


    return 0;

}