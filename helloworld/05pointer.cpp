using namespace std;
#include <iostream>
//指针

class Person {
    public: 
        int age;

};

void swap(int&x,int&y);

int main() {
    int *p = NULL;
    //空指针，值为0
    /*
    在大多数的操作系统上，程序不允许访问地址为 0 的内存，因为该内存是操作系统保留的。
    然而，内存地址 0 有特别重要的意义，它表明该指针不指向一个可访问的内存位置。
    但按照惯例，如果指针包含空值（零值），则假定它不指向任何东西。
    */
   //访问完空指针，下面的内容无法访问
    // cout << p << endl;
    //判断是否为空指针,
    if (!p) {
      cout << "empty pointer";  
    }


    // *p = 1;  这样赋值是有问题的
    //不为空指针
    if (p) {
        cout << "not empty pointer";
    }
    cout << "pointer ergodic array" << endl;
    int intArr[3] = {100,200,300};
    int *p1;
    p1 = intArr;
    for (int i = 0 ; i < 3 ; i++) {
        //注意，不能有中文的字符
        cout << "var[" << i << "]memory address is " << p1 << ",value:" << *p1 << endl;
        //移动位置
        p1++;
    }
    //注意：数组和指针(下面这样写法是错误的)，指针运算符 * 应用到 var 上是完全可以的，但修改 var 的值是非法的。这是因为 var 是一个指向数组开头的常量，不能作为左值
    for (int i = 0 ; i < 3 ; i++) {
        //赋值操作
       *(intArr + i) = i * 100;
    }

    cout << intArr[0] << endl;
    
    //指针数组
    int intArr2[3] = {100,200,300};
    int *pArr[3];
    for (int i = 0 ; i < 3 ; i++) {
        pArr[i] = &intArr[i]; // 赋值为整数的地址
    }
    for (int i = 0 ; i < 3 ; i++) {
        //地址 ，值
        cout << pArr[i] << "," << *pArr[i] << endl;
    }
    const char *names[4] = {
        "Zara Ali",
                   "Hina Ali",
                   "Nuha Ali",
                   "Sara Ali", 
    };
    for (int i = 0 ;i < 4 ; i++) {
        cout << &names[i] << "," <<names[i] << endl;
    }
    cout << "pointer's pointer" << endl;
    //指针的指针
    int **pp;
    int *p3;
    int var = 3000;
    p3 = &var;
    pp = &p3;

    cout << *p3 << endl;
    cout << **pp << endl;
    
    /*
    c++中的引用
    引用很容易与指针混淆，它们之间有三个主要的不同：
    不存在空引用。引用必须连接到一块合法的内存。
    一旦引用被初始化为一个对象，就不能被指向到另一个对象。指针可以在任何时候指向到另一个对象。(验证的时候不是这样的啊)
    引用必须在创建时被初始化。指针可以在任何时间被初始化。
    */
    cout << "create a quote" << endl;
    int i = 17;
    double d;
    int& r = i;
    double& s = d;

    i = 5;
    cout << "i:" << i <<endl;
    cout << "r reference:" << r <<endl;
    d = 11.7;
    cout << "d:" << d <<endl;
    cout << "s reference:" << s <<endl;

    int i2 = 18;
    r = i2;
    cout << "r reference:" << r <<endl;

    Person per;
    per.age = 10;
    Person per2;
    per2.age = 20;

    Person& pr = per;
    cout << pr.age << endl;
    pr = per2;
    cout << pr.age << endl;

    
    int a = 100;
    int b = 200;
    swap(a,b);
    cout << a << "," << b << endl;

    return 0;
}

void swap(int& x,int& y) {
    int temp;
    temp = x;
    x = y;
    y = temp;

    return;
}
