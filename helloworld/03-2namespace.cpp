#include <iostream>
using std::cout;
//演示可以使用std下面的count空间，不过想使用std中内容时，就必须写std::
// int main() {
//     cout << "std::endl is used with std!" << std::endl;
//     return 0;
 
// }

 //嵌套的命名空间()
namespace s1 {
    namespace s2 {
        void my() {
            cout << "s1->s2 my()" << std::endl;
        }
    }
}

//嵌套的命名空间(使用方式1)  注意：命名空间必须要定义在上面
// int main() {
//     s1::s2::my();
//     return 0;

// }

//嵌套的命名空间(使用方式2)
using namespace s1::s2;
int main() {
    my();
    return 0;

}

