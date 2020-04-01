#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {

    vector<string> msg {"Hello","C++","world","from"};
    for (const string & word : msg) {
        cout << word << " ";
    }


    cout << endl;

}
