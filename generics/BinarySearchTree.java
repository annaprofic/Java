package generics;

import java.util.Collection;

public class BinarySearchTree<E extends Comparable<? super E>> implements Collection<E> {

    private Node<E> root;
    private int nodesNumber;



    public BinarySearchTree(){
        this.nodesNumber = 0;
        root = null;
    }

    public boolean add(E x){
        boolean nodeAdded = false;
        Node<E> newNode = new Node<>(x);

        if (root == null) {
            root = newNode;
            nodesNumber++;
            nodeAdded = true;
        }

        else {
            Node<E> iterator = root;
            Node<E> parent = null;
            while(iterator != null) {
                parent = iterator;
                if (iterator.getValue().compareTo(x) > 0) iterator = iterator.leftNode;
                else iterator = iterator.rightNode;
            }
            iterator = newNode;
            nodesNumber++;
            if (parent.getValue().compareTo(x) > 0) parent.leftNode = iterator;
            else parent.rightNode = iterator;
            nodeAdded = true;
        }
        return nodeAdded;
    }

    public BinaryIterator<E> iterator() {
        return new BinaryIterator<>(root);
    }


    @Override
    public Object[] toArray() {
        Object[] array = new Object[nodesNumber];

        inOrder(array, root, 0);

        return array;
    }


    int inOrder(Object[] arr, Node<E> node, int i) {
        if(node != null) {
            i = inOrder(arr, node.leftNode, i);
            arr[i++] = node.getValue();
            return inOrder(arr, node.rightNode, i);
        }
        return i;
    }

    @Override
    public boolean remove(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] args) {
        BinarySearchTree<OrderedPair<String, String>> list = new BinarySearchTree<>();

        list.add(new OrderedPair<>("b", "slod"));
        list.add(new OrderedPair<>("c", "hello"));
        list.add(new OrderedPair<>("1", "world"));
        list.add(new OrderedPair<>("ani a", "s"));
        list.add(new OrderedPair<>("yui", "tata"));

        for(Object o: list)
            System.out.println(o);
    }
}
