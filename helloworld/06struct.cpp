using namespace std;
#include <iostream>
#include <cstring>

struct Book {
    char title[50];
    char author[50];
    int book_id;
    double price;
};
//typedef关键字
typedef struct Book2 {
    char title[50];
    char author[50];
    int book_id;
    double price;
} Book2;

//结构体作为函数参数
void printBook(struct Book book);
int main() {
    Book b1;
    Book b2;
    strcpy(b1.title,"java");
    strcpy(b2.title,"c");

    b1.book_id = 100;

    cout << b1.title << endl;

    printBook(b1);
    //指向结构体的指针
    struct Book *pBook1;

    pBook1 = &b1;

    //通过指针访问
    cout << pBook1 -> title << "," << pBook1 -> book_id << endl;

    //typedef定义
    Book2 b;
    

}
void printBook(struct Book book) {
    cout << "book title : " << book.title <<endl;
   cout << "book author : " << book.author <<endl;
   cout << "book ID : " << book.book_id <<endl;
}