#include <iostream>
using namespace std;


int main() {

    int *p;
    int i = 1;
    *p = i;
    cout << "address is:" << p <<",value is:" << *p << endl;
    int *p2[10];
    for (int j = 0 ; j < 10 ; j++) {
        *p2[j] = j;
        cout << "address is:" << (p2 + j) <<",value is:" << *(p2 + j) << endl;
    }

    return 0;
}
