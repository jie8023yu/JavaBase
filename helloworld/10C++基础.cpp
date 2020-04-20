#include<iostream>   //标准输入输出流
using namespace std;  //命名空间，使用命名空间std打开一个std房间


//使用一个宏
// #define _CRT_SECURE_NO_WARNINGS



/**
 * hello world  入门程序
 **/
void test1() {

    //endl 结束换行    c语言：\n \r\n
    std::cout << "hello wrold" << std::endl;
    cout << "hello world" << endl;


}
/**
 * cpp -> c plus plus
 * C++ 面向对象
 * 封装，继承，多态
 **/
int atk = 200;
void test2() {
    int atk = 100;
    cout << "atk:" << atk << endl;
    //双冒号 作用域运算符，::全局作用域
    cout << "atk:" << ::atk << endl;
}
//命名空间 begin--------------------------------------------start
/*
    主要为了区分命名冲突的问题
    命名空间下可以放函数，变量，结构体，类
    必须定义在全局作用域下
*/
namespace A{

    void fire() {
        cout << "A fire" << endl;
    }

    int A_m = 100;
}

//命名空间是开放的，可以随时在原先的命名空间添加内容
namespace A {
    int A_n = 200;
}
//匿名命名空间/无名命名空间，这里吗定义的只能在当前cpp中使用，相当于写static int m_c = 0;
namespace {
    int m_c = 0;
}
//命名空间可以起别名
namespace verylong {

    int v = 10;
} 

namespace B{
    void fire() {
        cout << "B fire" << endl;
    }
}
//命名空间 begin--------------------------------------------end

void test3(){
    A::fire();
    B::fire();
    cout << A::A_m << "," << A::A_n << endl;
    cout << "m_c:" << m_c << endl;
    //命名空间起别名
    namespace n1 = verylong;
    cout << n1::v << endl;

}

//using声明-------------------------------------------------------》
namespace n2{
    int age = 20;
}
namespace n3{
    int age = 30;
}
void test4() {
    int age = 10;
    cout << n2::age << endl;
    cout << age << endl;
    //这样写会导致一个二义性，冲突，编译不通过，using声明要注意避免二义性
    // using n2::age;
    cout << age << endl;
}
/*
using编译指令-------------------------------------------------------》

using编译指令，
    如果指定了多个空间，空间中同时有相同名称的变量，此时编译会报错，必须指定执行使用哪个命名空间下的变量（这个变量当前没有定义）
    如果这个变量也定义了，同时使用两个空间，不会报错，此时访问的数据就是当前函数中指定的局部变量了，不使用任何一个命名空间中的变量值

*/
void test5() {
    // int age = 10;
    cout << n2::age << endl;
    // cout << age << endl;
    //这样写就没问题了
    using namespace n2;
    // using namespace n3;
    cout << age << endl;
}
/*
c++对C的增强-------------------------------------------------------》
对比10C.c文件
*/
//全局变量这样定义，C++编译不通过
// int a;
// int a = 10;

//这样写，C++也可以编译通过
int get(int h,int w){

}
//编译直接报错了，C是可以直接通过的
void test() {
    // get(1,2,3);
}
//C++中，转换必须强制转换，不能直接赋值
void t2() {
    char *p = (char*)malloc(sizeof(64)); 
    
}
//C++中，结构体中是可以写方法的
struct Person {
    int age;

    //可以支持写方法
    void plusAge() {
        age++;
    }
};

void t3() {
    //C++可以不写
    Person p1;
    p1.plusAge();
    cout << p1.age << endl;
}
//C++中有bool类型  只有true false 非0的值转为1，0还是0
bool flag;

//C++三元运算符增强
void t4() {
    int a = 20;
    int b = 30;
    cout << "ret:" << (a > b ? a : b) << endl;

    a > b ? a : b = 100;
    cout << "b:" << b << endl;
} 

const int a = 10;  //这个跟C一样
void t5() {
    const int m_a = 20;
    // m_a = 10;  //编译不通过
    //这里操作的地址只是一个临时的内存空间，并不是真实的m_a的地址，C++做的，C中是真实分配的地址
    int *p = (int*)&m_a;  //此处必须强转
    *p = 30;
    //C++就不能这样改了，这样就成了常量了
    cout << "*p=" << *p << ",m_a=" << m_a << endl;
}

//C++也不支持
void t6() {
    // extern const int cc;
    // cout << "cc:" << cc << endl;
}
void test6() {
    cout << "test6-s" << endl;
    get(1,2);
    t3();
    t4();
    t5();
    // t6();
    cout << "sizeof(bool):" << sizeof(bool) << endl;
    cout << "test6-e" << endl;
}

//const分配内存
void test7() {
    const int m_a = 10;
    int *p = (int *)&m_a;  //分配临时内存

}

void  test8() {
    int a = 10;
    const int b = a; //分配内存

    cout << "b:" << b << endl;
    int *p = (int *)&b;

    *p = 20;

    cout << "a:" << b << endl;

}

#include<string.h>
//自定义数据类型
struct Per {
    string name; //姓名
    int age;
};

void test9() {
    //必须写初始化，不能直接写 const Per per1  why?????
    const Per per1 = {};  //这样写也会分配内存
    //不能这样写，编译不通过
    // p1.age = 100;
    //不是const的就可以这样写
    /* Per per2;
    Per *pPer = &per2; */

    //怎么修改
    Per *p = (Per *)&per1;
    //第一种修改方式写法
    p -> age = 20;
    //第二种修改方式写法
    (*p).name = "July";
    
    cout << "name:" << per1.name << ",age:" << per1.age << endl;

}

//尽量以const替换define #define MAX 1024;


/**
 * 引用 C++对C的扩展
 * reference 
 * 
 * 引用就是起别名
 * 
 * &写到左侧，叫引用  type &别名 = 原名
 * 写到右侧，取地址
 * 
 **/

void  test10() {    
    //引用基本语法
    int a = 10;
    int &b = a;
    b = 20;

    //引用必须初始化,不初始化会编译报错，下面这样写就有问题
    // int &c;

    //引用初始化后不能再修改了
    int aa = 20;
    cout << "a = " << a << endl;
    cout << "b = " << b << endl;

    //数组建立引用
    int arr[3] = {1,2,3};
    //定义引用数组，每个引用指向一个值
    int(&rArr)[3] = arr;
    
    for (int i = 0; i < 3; i++) {
        cout << rArr[i] << " ";
    }
    cout << endl;
    //第二种方式起别名
    typedef int(ARRAY)[3];  //具有3个元素的int类型的数组
    ARRAY &rArr2 = arr;
     for (int i = 0; i < 3; i++) {
        cout << rArr2[i] << " ";
    }
    cout << endl;

}
/**
 *参数3重传递方式
 1.值传递
 2.地址传递
 3.引用传递
 * */
void test11() {
    int a = 10;
    int b = 20;
    cout << "a:" << a << endl;
    cout << "b:" << b << endl;
    cout << "-------------------" << endl;
    swap(a,b);
    cout << "a:" << a << endl;
    cout << "b:" << b << endl;

}

//引用作为参数
void swap(int &a,int &b) {
    int temp = a;
    a = b;
    b = temp;
}

int& doWork() {
    int a = 10;
    return a;
}
/**
 * 引用注意事项
 * 1.必须引用一块合法内存空间
 * 2.不要返回局部变量的引用
 **/
void test12() {
    //错误写法
    // int &a = 10;

 /*    int &a = doWork();
    cout << a << endl;
    cout << a << endl;
    cout << a << endl;
    cout << a << endl; */
}
//引用本质 --> 就是一个指针常量
void test13() {

    int a = 10;
    int &pA = a;  //自动转换为：int* const pA = &a;  指针常量，所以必须初始化
    pA = 20 ;    //编译器发现aRef是引用，其实就是指针，自动转换为 *pA = 20;

}

struct Per2 {
    int age;
};

/*
**p  具体的Person对象
*p   *p对象的指针
p    指针的指针
*/
void allocateMemory(Per2 **p) {       
    *p = (Per2 *)malloc(sizeof(Per2));
    (*p) -> age = 100;
}
//指针引用开辟空间
void allocateMemory2(Per2* &p) {       
    p = (Per2 *)malloc(sizeof(Per2));
    p -> age = 200;
}
//指针引用
void test14() {
    Per2 *p = NULL;
    allocateMemory(&p);
    cout << (*p).age << endl;

    Per2 *p2 = NULL;
    allocateMemory2(p2);
    cout << (*p2).age << endl;


}
//常量引用,用来修饰形参
void test15() {
    // int &ref = 10; //这样写不合法

    const int &ref = 10; //加入const后，编译器处理方式为：int temp = 10;const int &ref = temp;

    cout << ref << endl;

    int *p = (int *)&ref;
    *p = 20;

    cout << ref << endl;
}

void tt(const int &a) {

}


/**
 * 宏函数的缺陷
 * 1.不加括号，运行会有问题
 * 2.加括号了，运算会重复执行
 * 3.没有作用域
 * 
 * 为甚么有宏函数？？？？
 * 代码比较少，不想定义一个函数，还要入栈，出栈，所以定义一个宏函数，来直接替换
 **/
#define MyAdd(x,y) x + y
#define myAdd2(x,y)((x) + (y))

#define com(a,b) ((a) < (b) ? (a) : (b))

/**
 * 由于宏函数有上述缺陷，所以C++引进了一个内联函数，效果跟宏函数是一样的，直接替换
 * 空间换时间
 * 之前com2()就是一个函数调用，现在需要把这么大一串替换上，空间变大，运行时间变短？
 * 正常函数调用会有一个压栈，出栈的过程，这个操作是需要时间开销的，使用内联减少了这个开销
 **/
inline int com2(int a,int b){
    int ret = a < b ? a : b;
    return ret;
} 
/*
内联函数注意事项：
内联函数声明 inline *******
类内部的成员函数，默认前面会加inline关键字
内联函数编译有要求（出现下列情况，不会进行内联编译）：
    不能存在任何形式的循环
    不能存在过多的条件判断语句
    函数体不能过于庞大（编译器自己判断，没有一个量化的标准）
    不能对函数进行取址函数

内联函数解决宏缺陷的问题，给编译器建议，加上，不一定按照内联处理，不加，可能会把某些按照内联处理。
*/

void test16() {
    int ret = MyAdd(10,20);
    cout << "ret:" << ret << endl;
    ret = MyAdd(10,20) * 20 ;  //预期结果600，实际是410，怎么算的，将 10 + 20 * 20 = 410
    cout << "ret:" << ret << endl;
    //改成myAdd2就可以了
    ret = myAdd2(10,20) * 20 ;  
    cout << "ret:" << ret << endl;

    int a = 10;
    int b = 20;
    ret = com(a,b);  //这样可以正常显示
    cout << "ret:" << ret << endl;
    ret = com(++a,b);  //预期结果11，实际是12 ++a都会当做操作，计算
    cout << "ret:" << ret << endl;

    //使用内联函数
    a = 10;
    ret = com2(++a,b);
     cout << "ret:" << ret << endl;
}

/*函数默认参数(C++支持)
    如果有一个位置有了默认值，从这个参数开始，往右的所有参数都必须有默认值
*/
void tt17(int a= 10,int b = 20) {
    cout << a << "," << b << endl;
}

void test17() {
    tt17();
}

//函数，占位参数,函数调用必须提供这个函数，但是用不到，没什么用处 ，重载会有一些用处，占位参数，可以有默认值
void test18(int) {
}
void test19(int = 1){

}

/**
 * 函数重载
 * 
 * C++中，函数可以同名称，但是参数个数不同，或者类型不同，或者顺序不同
 * 函数必须都在同一个作用域
 * 
 **/
void func() {

    cout << "func()" << endl;
}

void func(int a) {
    cout << "func(int a)" << endl;
}
void func(double a) {
    cout << "func(double a)" << endl;
}
//引用的重载版本
void func2(int &a) {

}
void func2(const int &c) {

}

/*函数重载原理：
    void func(){}
    void func(int i){}
    void func(intx,char y){}
    这三个函数在Linux下编译之后的函数名为：
    _Z4funcv
    _Z4funci
    _Z4funcic
*/


void test20() {
    func();
    func(2);
    func(1.3);
}


/*
extern C 
C++去调用 10test.h中定义的show()方法，在10C_1.c中实现的
extern "C" 必须这样设置

extern没有实现出来
*/
// #include "10C.h"
//这样定义了，就不能再引用头文件了，会报重复的报错
// extern "C" void show();
#include "10C.h"
void test21() {
    //直接这样去写，编译会报找不到这个引用
    // show();  //在C++中，函数是可以发生重载的，编译器会把这个函数名称改变，
    //通过extern设置后，再调用就没有问题了
    show();
}


/**
 * 封装
 * 
 * 访问权限：
 *  public
 *  protected
 *  private
 * 
 */
struct Person2 {
    //不声明权限，默认是public
    char name[64];
    int age;

    void eat() {

    }
};

struct Dog{
    char name[30];
    int age;

    void eatDog() {

    }
};
//struct和class是一个意思，唯一的不同是，默认权限，struct是public，而class是private
class Animal {

    void eat() {

    }

    public:
        void eat2() {

        }
};
//C++的封装，将属性和行为封装在一次，而C是将属性和行为分离开的，只有属性是封装在一起
void test22() {
    Person2 p1;
    // p1.eatDog();  //不能这样去调用

    Animal a;
    // a.eat();  //如果不是公共的不能访问

    a.eat2();   //公共的在类内部和外部都能访问

}


class Person3 {

    
    public :
        //构造函数写法
        Person3()  {
            cout << "person3()" << endl;
        }
        //析构函数(不能重载，不能有参数)
        ~Person3() {
            cout << "~Person3()" << endl;
        }
    int age;
    
};
/**
 * 对象的初始化和清理（构造和析构函数）
 * 构造和析构函数都是编译器自动调用的
 */
void test23() {
    Person3 p;
}

/**
 * 构造函数的分类和调用
 * 无参构造函数
 * 有参构造函数
 * 普通构造函数
 * 拷贝构造函数
 **/
class Person4 {

    public:
        int age;
        //无参构造
        Person4() {
            cout << "Person4()" << endl;
        }
        //析构函数
        ~Person4() {
            cout << "~Person4()" << endl;
        }
        //有参构造函数
        Person4(int a) {
            cout << "Person4(int a)" << endl;   
        }
        //拷贝构造函数
        Person4(const Person4& p) {
            age = p.age;
            cout << "Person4(const Person4& p)" << endl;
        }
};

void test24() {
    //构造函数调用方式
    //括号方式调用
    Person4 p1;  //默认构造函数不加括号  Person4 p1();  这样写，被认为是声明
    Person4 p2(3);
    p2.age = 10;
    Person4 p3(p2);
    cout << "p3.age is " << p3.age << endl;
    //显式调用
    Person4 p4 = Person4(5);
    //匿名对象，在这行代码执行后，就释放了
    Person4(100);   
    cout << "end" << endl;
    //不能使用拷贝函数来初始化匿名对象
    // Person4(p4);    //编译器认为写成了 Person4 p4;  对象的声明
    // Person4 p5 = Person4(p4);   //这样写就可以了

}


void tt25(Person4 p) {    //这里实际上就相当于 Person4 p = Person(p2);

}

Person4 tt25_1() {
    Person4 p1;
    return p1;
}

/**
 * 拷贝构造函数的调用时机
 **/
void  test25() {
    //1。用一个已经创建好的对象来初始化另一个对象
    Person4 p1;
    p1.age = 20;
    Person4 p2(p1);
    //2。以值传递的方式给函数参数传值
    tt25(p2);  //调用
    //3.以值的方式返回局部对象(编译器会做优化，此处) Person4 pp; tt25_1(pp);  void tt25_1(Person& p) { Person p;} 实际测试不会打出拷贝构造函数
    cout << "--------------------------------s" << endl;
    Person4 pp = tt25_1();
    cout << pp.age;
    cout << "--------------------------------e" << endl;
}
/**
 * 构造函数调用规则
 * 当提供了有参构造函数，系统就不会提供默认的构造函数了
 * 但是系统会提供默认的拷贝构造
 * 系统默认给一个类提供三个函数：
 *  默认构造
 *  拷贝构造
 *  析构构造，进行简单的值拷贝
 * 当提供了拷贝构造，系统就不会提供其他构造了
 */
void  test26() {

}
/**
 * 深拷贝/浅拷贝
 **/
class Cat {
    public:
        char * cname;
        int cage;

        Cat() {

        }
        Cat(char * name,int age) {
            cname = (char *)malloc(strlen(name) + 1); //开辟空间
            strcpy(cname,name);
            cage = age;

        }
        ~Cat() {
            if (cname != NULL) {
                free(cname);
                cname = NULL;
            }
             cout << "~Cat()" << endl;
        }
        //拷贝构造，系统默认提供一个，自己提供一个，进行深拷贝
        Cat(const Cat& cat) {
            cage = cat.cage;
            cname = (char *)malloc(strlen(cat.cname) + 1); //开辟空间
            strcpy(cname,cat.cname);
        }
};
void test27() {
    Cat c1("lucy",12);
    Cat c2(c1);  //拷贝构造,浅拷贝，在析构的时候会有问题
    cout << "cname:" << c2.cname << endl;
}









//函数入口地址
int main() {
    // test1();
    // test2();
    // test3();
    // test4();

    // test6();

    // test8();

    // test9();

    // test10();

    // test11();

    // test12();

    
    // test14();

    // test15();

    // test16();

    // test17();

    // test20();

    // test21();
    

    // test23();

    // test24();

    // test25();


    test27();


    return 0;
}

