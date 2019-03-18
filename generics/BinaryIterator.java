package generics;

import java.util.Iterator;
import java.util.Stack;

public class BinaryIterator<E extends Comparable<? super E>> implements Iterator<E> {
    private Stack<Node<E>> stack;

    public BinaryIterator(Node<E> root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.leftNode;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        Node<E> node = stack.pop();
        E result = node.getValue();
        if (node.rightNode != null) {
            node = node.rightNode;
            while (node != null) {
                stack.push(node);
                node = node.leftNode;
            }
        }
        return result;
    }
}
