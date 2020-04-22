#include<iostream>

using namespace std;

template<class t1,class t2>
class Apple {
    public:
        Apple() {

        }

        Apple(t1 age,t2 color);
        int age;
        int color;

        //定义/声明函数
        void showColor();
};

template<class t1,class t2>
Apple<t1,t2>::Apple(t1 age,t2 color) {
    this -> age = age;
    this -> color = color;
}
template<class t1,class t2>
void Apple<t1,t2>::showColor(){
    cout << this -> color << endl;
}