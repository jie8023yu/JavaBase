//数组封装
#include<iostream>
using namespace std;
class MyArray {

    public:
        MyArray();  //默认构造，100个容量
        MyArray(int capacity);
        MyArray(const MyArray& myArray);
        ~MyArray();
        
        void push(int element);
        int getData(int index);

        int getSize();
        int getCapacity();
    private:
        int capacity; //数组容量
        int size; //当前数组数量
        int *cur; //当前指针指向
};