#include <iostream>
using namespace std;
class Parent {
    protected:
        int age;
        int salary;
    
    public:
        //the param is not same name 
        void setAge(int a) {
            age = a;
        }
        void setSalary(int s) {
            salary = s;
        }
}; 
//extend
class Sub:public Parent {
    public:
        int getMy() {
            return (age * salary);
        }
};

int main(void) {
    Sub sub;
    sub.setAge(20);
    sub.setSalary(10);

    cout << "my salary:" << sub.getMy() << endl;

    return 0;
}