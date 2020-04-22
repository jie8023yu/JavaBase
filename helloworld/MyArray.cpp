#include "MyArray.h"
#include "string.h"

//具体实现
MyArray::MyArray() {
    this -> capacity = 100;
    this -> size = 0;
    this -> cur = new int[this -> capacity];
    memset(cur,0,capacity * sizeof(int));
}
MyArray::MyArray(int capacity) {
    this -> capacity = capacity;
    this -> size = 0;
    this -> cur = new int[this -> capacity];
    memset(cur,0,capacity * sizeof(int));
}
//拷贝构造
MyArray::MyArray(const MyArray& myArray) {
    cout << "MyArray(const MyArray& myArray)" << endl;
    this -> cur = new int[myArray.capacity];
    this -> size = myArray.size;
    this -> capacity = myArray.capacity;
    //进行数组赋值操作
    memcpy(cur,myArray.cur,capacity * sizeof(int));
}

MyArray::~MyArray() {
    cout << "~MyArray()" << endl;
    if (this -> cur != NULL) {
        delete[] this -> cur;  //注意：是否数组内存空间一定要这样写
        this -> cur = NULL;
    }
}
//注意写法
void MyArray::push(int element) {
    cur[size++] = element;
}

int MyArray::getData(int index) {
    return cur[index];
}

int MyArray::getSize() {
    return size;
}

int MyArray::getCapacity() {
    return capacity;
}
