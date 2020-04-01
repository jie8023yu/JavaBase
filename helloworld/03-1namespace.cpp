#include <iostream>
using namespace std;
//namespace  相当于java中的不同包
namespace s1 {

    void func() {
        cout << "s1 namespace func()" << endl;
    }
}

namespace s2 {
   void func() {
        cout << "s2 namespace func()" << endl;
    } 
}

// int main() {
//     s1::func();

//     s2::func();

//     return 0;
// }

//指定使用的命名空间
using namespace s2;
int main() {
    func();

    return 0;
}