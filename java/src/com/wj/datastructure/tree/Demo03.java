package com.wj.datastructure.tree;

public class Demo03 {

    public static void main(String[] args) {
        BinarySearchTree<Person> tree = new BinarySearchTree<Person>();
        tree.setRootNode(new BinaryNode<Person>(new Person(15,"test1")));
        tree.add(new Person(12,"test2"));
        tree.add(new Person(17,"test3"));
        tree.add(new Person(13,"test4"));
        tree.add(new Person(11,"test5"));
        tree.add(new Person(16,"test6"));
        tree.add(new Person(20,"test7"));
        tree.add(new Person(21,"test8"));
        System.out.println(1);

        tree.remove(new Person(17,"test1"));
    }

}

class Person implements Comparable<Person>{

    private int age;
    private String name;


    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.age - o.getAge();
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
