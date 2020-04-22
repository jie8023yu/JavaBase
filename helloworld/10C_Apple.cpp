#include<iostream>
using namespace std;

#include "10C_2.h"

template<class t1,class t2>
Apple<t1,t2>::Apple(t1 age,t2 color) {
    this -> age = age;
    this -> color = color;
}
template<class t1,class t2>
void Apple<t1,t2>::showColor(){
    cout << this -> color << endl;
}