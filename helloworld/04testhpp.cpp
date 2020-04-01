using namespace std;
#include <iostream>
#include "test.hpp"



class BImpl : public B {
    public:
        void func(A a) {
            cout << "BImpl" <<endl;
        }
};
class AImpl : public A {
    void func() {
        cout << "AImpl" <<endl;
    }
};
int main() {
    BImpl b;
    AImpl a;
    b.func(a);
    return 0;
}

