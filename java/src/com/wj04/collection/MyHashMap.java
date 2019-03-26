package com.wj04.collection;

public class MyHashMap {

    Node[] table;  //位桶 bucker array
    int size;  //存放的键值对个数

    public MyHashMap(){
        table = new Node[16];  //长度2的整数幂
    }

    public void put(Object key,Object value){
        Node newNode = new Node();
        newNode.hash = myHash(key.hashCode(),table.length);
        newNode.key = key;
        newNode.value = value;
        newNode.next = null;

        Node temp = table[newNode.hash];
        Node lastNode = null;
        boolean keyRepeat = false;
        if (null == temp) {
            table[newNode.hash] = newNode;
        } else {
            //如果不为空，遍历链表
            while (null != temp) {
                if (temp.key.equals(key)) {
                    temp.value = newNode.value;
                    keyRepeat = true;
                    break;
                } else {
                    lastNode = temp.next;
                    temp = lastNode;
                }
            }
            if (!keyRepeat) lastNode.next = newNode;
        }


    }

    public int myHash(int v,int length){
        System.out.println(v & (length - 1));  //直接位运算，效率高，取余效率低
        return v & (length - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0;i < table.length;i++) {
            Node temp = table[i];

            while (null != temp) {
                sb.append(temp.key + ":" + temp.value + ",");
                temp = temp.next;
            }
        }

        sb.setCharAt(sb.toString().length() - 1,'}');

        return sb.toString();
    }
}
