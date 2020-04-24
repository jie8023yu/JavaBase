#include<iostream>
using namespace std;
class An3 {
    public:
        virtual void speak() {
            cout << "an3 speak() " << endl;
        }
        virtual void s2() {
            cout << "an3 s2()" << endl;
        }
};
class Cat1 : public An3 {
    public:
        void speak() {
            cout << "Cat1 speak()" << endl;
        }
        void s2() {
            cout << "cat1 s2()" << endl;
        }
};