package generics;

public class Node<E extends Comparable<? super E>> {
    E value;
    Node<E> leftNode;
    Node<E> rightNode;

    public Node(E value) {
        leftNode = null;
        rightNode = null;
        this.value = value;
    }

    public Node (Node<E> other){
        this.value = other.getValue();
        this.leftNode = other.getLeftNode();
        this.rightNode = other.getRightNode();
    }

    E getValue() {
        return value;
    }

    private Node<E> getLeftNode(){
        return leftNode;
    }

    private Node<E> getRightNode(){
        return rightNode;
    }
}
