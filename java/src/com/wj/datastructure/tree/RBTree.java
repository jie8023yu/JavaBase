package com.wj.datastructure.tree;

/**
 * 红黑树实现
 * 红黑树四大性质：
 * 1.红黑树节点的颜色非红即黑
 * 2.红色节点的两个子节点必须是黑色
 * 3.叶子都为黑色（这里的叶子节点是指NULL节点）
 * 4.每个节点到叶子节点的所有路径包含的黑色节点个数要相同
 */
public class RBTree {

    private RBNode rootNode;

    private final static boolean RED = false;
    private final static boolean BLACK = true;

    public RBTree() {
    }

    public RBTree(RBNode rootNode){
        this.rootNode = rootNode;
    }

    public RBTree(int data){
        this.rootNode = new RBNode(data,true);
    }

    public RBNode parentOf(RBNode node){
        if (null != node) return node.getParent();
        return null;
    }

    public boolean colorOf(RBNode node){
        if (null != node) return node.isColor();
        return BLACK;
    }

    public void setParent(RBNode node,RBNode parent){
        if (null != node) node.setParent(parent);
    }

    public void setColor(RBNode node,boolean color){
        if (null != node) node.setColor(color);
    }

    public boolean isRed(RBNode node){
        return (null != node && node.isColor() == RED) ? true : false;
    }

    public boolean isBlack(RBNode node){
        return !isRed(node);
    }

    public void setRed(RBNode node){
        if (null != node) node.setColor(RED);
    }

    public void setBlack(RBNode node){
        if (null != node) node.setColor(BLACK);
    }

    /**
     * 寻找为key值的结点
     * @param key
     * @param node
     * @return
     */
    public RBNode search(int key,RBNode node){
        if (null != node) {
            int comparison = key - node.getData();
            if (comparison < 0) return search(key,node.getLeftChild());
            else if (comparison > 0) return search(key,node.getRightChild());
            else return node;
        }
        return null;
    }

    /**
     * 寻找后继结点，即大于该结点的最小结点
     * @param node
     * @return
     */
    private RBNode min(RBNode node){
        if (null != node) {
            if (node.getLeftChild() == null) return node;
            while (node.getLeftChild() != null) node = node.getLeftChild();
        }

        return node;
    }

    /**
     * 对某个结点进行左旋
     * @param x
     */
    private void leftRonate(RBNode x){
        RBNode y = x.getRightChild();
        if (y.getLeftChild() != null) {
            y.getLeftChild().setParent(x);
        }

        x.setRightChild(y.getLeftChild());
        y.setLeftChild(x);
        y.setParent(x.getParent());

        if (null != x.getParent()) {
            if (x == x.getParent().getLeftChild()) x.getParent().setLeftChild(y);
            else x.getParent().setRightChild(y);
        } else this.rootNode = y;

        x.setParent(y);
    }

    /**
     * 对某个结点右旋
     * @param x
     */
    private void rightRonate(RBNode x){
       RBNode y = x.getLeftChild();

       if (null != y.getRightChild()) y.getRightChild().setParent(x);

       y.setParent(x.getParent());
       x.setLeftChild(y.getRightChild());
       y.setRightChild(x);

       if (null != x.getParent()) {
           if (x.getParent().getLeftChild() == x) x.getParent().setLeftChild(y);
           else x.getParent().setRightChild(y);
       } else this.rootNode = y;

       x.setParent(y);
    }


    private void insertFixUp(RBNode node){
        RBNode parent,gparent;
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            gparent = parentOf(parent);
            if (gparent.getLeftChild() == parent) {
                RBNode uncle = gparent.getRightChild();
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);

                    node = gparent;
                    continue;
                } else {
                    if (node == parent.getRightChild()) {
                        leftRonate(parent);
                        RBNode temp = node;
                        node = parent;
                        parent = temp;
                    }

                    setBlack(parent);
                    setRed(gparent);
                    rightRonate(gparent);
                }
            } else {
                RBNode uncle = gparent.getLeftChild();
                if (isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);

                    node = gparent;
                    continue;
                } else {
                    if (node == parent.getLeftChild()) {
                        rightRonate(parent);
                        RBNode temp = node;
                        node = parent;
                        parent = temp;
                    }

                    setBlack(parent);
                    setRed(gparent);
                    leftRonate(gparent);
                }
            }
        }

        if (node == rootNode) setBlack(node);
    }

    /**
     * 红黑树添加操作
     * @param node
     */
    public void add(RBNode node) {
        int comparison;
        RBNode x = this.rootNode;
        RBNode y = null;
        while ( null != x ){
            y = x;
            comparison = node.getData() - x.getData();

            if ( comparison < 0)
                x = x.getLeftChild();
            else
                x = x.getRightChild();
        }

        node.setParent(y);
        if (null != y) {
            comparison = node.getData() - y.getData();
            if (comparison < 0)
                y.setLeftChild(node);
            else
                y.setRightChild(node);

        } else {
            this.rootNode = node;
        }

        setRed(node);
        insertFixUp(node);
    }

    public void add(int data){
        add(new RBNode(data,BLACK));
    }

    /**
     * 红黑树删除操作
     * @param node
     */
    private void delete(RBNode node){

        RBNode child,parent,replace;
        boolean color = BLACK;
        if (null != node.getLeftChild() && null != node.getRightChild()) {
            replace = successor(node);
            parent = parentOf(replace);
            child = replace.getRightChild();
            color = colorOf(replace);
            if (node == parentOf(replace)) {
                parent = replace;
            } else {
                if (null != child) {
                    setParent(child,parentOf(replace));
                }

                replace.getParent().setLeftChild(child);
                replace.setRightChild(node.getRightChild());
                setParent(node.getRightChild(),replace);
            }
            setParent(replace,parentOf(node));
            replace.setLeftChild(node.getLeftChild());
            setParent(node.getLeftChild(),replace);
            setColor(replace,colorOf(node));

            if (null != parentOf(node)) {
                if (node == node.getParent().getLeftChild()) {
                    node.getParent().setLeftChild(replace);
                } else {
                    node.getParent().setRightChild(replace);
                }
            } else {
                this.rootNode = replace;
            }

            if (BLACK == color){
                deleteFixUp(child,parent);
            }
        } else {
            if (null != node.getLeftChild()) {
                replace = node.getLeftChild();
            } else {
                replace = node.getRightChild();
            }

            parent = parentOf(node);
            if (null != parent) {
                if (node == parent.getLeftChild()) {
                    parent.setLeftChild(replace);
                } else {
                    parent.setRightChild(replace);
                }
            } else {
                this.rootNode = replace;
            }

            setParent(replace,parent);

            color = colorOf(node);
            child = replace;

            if (color == BLACK) deleteFixUp(child,parent);
        }

    }

    public void delete(int key){
       RBNode node = search(key,rootNode);
       if (node != null) delete(node);
    }

    private RBNode successor(RBNode node){
        if (null != node.getRightChild()){
            return min(node.getRightChild());
        }

        RBNode nodeParent = node.getParent();
        while (null != nodeParent && nodeParent.getRightChild() == node) {
            node = nodeParent;
            nodeParent = nodeParent.getParent();
        }

        return nodeParent;
    }

    /**
     * 红黑树删除恢复
     * @param node
     * @param parent
     */
    private void deleteFixUp(RBNode node,RBNode parent){
        RBNode other;
        while (isBlack(node) && node != this.rootNode){
            if (node == parent.getLeftChild()) {
                other = parent.getRightChild();
                if (isRed(other)){
                    setRed(parent);
                    setBlack(other);
                    leftRonate(parent);
                    continue;
                } else {
                    if (isBlack(other.getLeftChild()) && isBlack(other.getRightChild())){
                        setRed(other);
                        node = parent;
                        parent = parentOf(node);
                    } else if (isRed(other.getLeftChild()) && isBlack(other.getRightChild())) {
                        setRed(other);
                        setBlack(other.getLeftChild());
                        rightRonate(other);
                    } else if (isRed(other.getRightChild())) {
                        setColor(other,colorOf(parent));
                        setBlack(parent);
                        setBlack(other.getRightChild());
                        leftRonate(parent);
                        break;
                    }
                }
            } else {
                other = parent.getLeftChild();
                if (isRed(other)) {
                    setBlack(other);
                    setRed(parent);
                    rightRonate(parent);
                    continue;
                } else {
                    if (isBlack(other.getLeftChild()) && isBlack(other.getRightChild())){
                        setRed(other);
                        node = parent;
                        parent = parentOf(node);
                    } else if (isRed(other.getRightChild()) && isBlack(other.getLeftChild())){
                        setRed(parent);
                        setBlack(other.getRightChild());
                        leftRonate(other);
                    } else if (isRed(other.getLeftChild())){
                        setColor(other,colorOf(parent));
                        setBlack(parent);
                        setBlack(other.getLeftChild());
                        rightRonate(parent);
                        break;
                    }
                }
            }
        }

        setBlack(node);
    }




}
class RBNode {
    private int data;
    private boolean color;  //false,红色，true黑色
    private RBNode parent;
    private RBNode leftChild;
    private RBNode rightChild;

    public RBNode() {
    }


    public RBNode(int data, boolean color) {
        this.data = data;
        this.color = color;
    }

    public RBNode(int data, boolean color, RBNode parent, RBNode leftChild, RBNode rightChild) {
        this.data = data;
        this.color = color;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }


    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public RBNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBNode leftChild) {
        this.leftChild = leftChild;
    }

    public RBNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBNode rightChild) {
        this.rightChild = rightChild;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
