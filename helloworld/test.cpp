#include <iostream>
using namespace std;
 
// main() 是程序开始执行的地方

class Box {

   public :
      double length;
      double width;
      double hegiht;
};
int main()
{

   double volume = 0.0;
   
   //cout << "Hello World"; // 输出 Hello World
   //return 0;

   Box box1;
   Box box2;
   box1.hegiht = 10.0;
   box1.width = 10.0;
   box1.length = 1.0;
   box2.hegiht = 200.0;
   box2.width = 20.0;
   box2.length = 20.0;

   volume = box1.hegiht * box1.width * box1.length;
   cout << "box1���Ϊ��" << volume << endl;
   volume = box2.hegiht * box2.width * box2.length;
   cout << "box2���Ϊ��" << volume << endl;

   // cout << "hello";

   return 0;
}

