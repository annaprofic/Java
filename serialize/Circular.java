import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unchecked")
public class Circular<E extends Serializable> implements Serializable, BlockingQueue<E> {

    private E[] buf;
    private int totalSize;
    private int tail;
    private int head;
    private int currentSize;

    Circular(int size) {
        this.totalSize = size;
        buf = (E[]) new Serializable[size];
        currentSize = 0;
        tail = 0;
        head = 0;

    }

    public String toString(){
        if(currentSize == 0) throw new ArrayIndexOutOfBoundsException("Queue is empty.");
        StringBuilder s = new StringBuilder();
        String value;
        for (int i = 0; i < buf.length; i++){
            int distanceToHead = Math.abs(tail - i);
            value = (buf[i] == null) ? "Empty" : (String) buf[i];
            s.append("| index: ").append(i).append(" |  | distance to head: ").append(distanceToHead);
            s.append("|  | type: ").append(buf.getClass().getSimpleName()).append(String.format(" |  | value: %-12s |\n", value));

        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularIterator(this);
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public synchronized void put(E e) throws InterruptedException {
        if(isFull()) this.wait();
        if (tail == totalSize) tail = 0;
        if (size() == 0){
            offer(e);
            this.notify();
        } else offer(e);
    }

    @Override
    public boolean offer(E e) {
        if (isFull()) return false;
        if (tail == totalSize) tail = 0;
        buf[tail] = e;
        currentSize++;
        tail++;
        return true;
    }


    @Override
    public E remove() {
        return poll();
    }

    @Override
    public synchronized E take() throws InterruptedException {

        if(isEmpty()) this.wait();
        if(currentSize == totalSize) {
            this.notify();
            return poll();
        } else return poll();
    }

    @Override
    public E poll() {
        if (isEmpty()) return null;
        E temp = buf[head];
        if(head == totalSize) head = 0;
        buf[head++] = null;
        currentSize--;
        return temp;
    }


    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        this.totalSize = (int)input.readObject();
        this.currentSize = (int)input.readObject();
        this.buf = (E[]) new Serializable[currentSize];
        for(int i = 0; i < currentSize; i++){
            this.buf[i] = (E) input.readObject();
        }
        this.head = 0;
    }

    private void writeObject(ObjectOutputStream output) throws IOException {
        output.writeObject(totalSize);
        int head_ = head;
        output.writeObject(size());
        for(int i = 0; i < totalSize ; i++) {
            if (head_ == buf.length) head_ = 0;
            output.writeObject(this.buf[head_++]);
        }
    }


    private boolean isFull() {
        return (currentSize == totalSize);
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public int size() {
        return currentSize;
    }


    @Override
    public Object[] toArray() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int size = size();
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        } else if (array.length > size) {
            array[size] = null;
        }

        int i = 0;
        for (E e: this) {
            array[i] = (T) e;
            i++;
        }
        return array; 
    }

    class CircularIterator implements Iterator<E> {
        private int head;
        private int checkedElements;

        CircularIterator(Circular<E> buf) {
            head = buf.head;
        }

        @Override
        public boolean hasNext() {
            if (head == totalSize) head = 0;
            return (buf[head] != null && checkedElements < currentSize);
        }

        @Override
        public E next() {
            checkedElements++;
            return buf[head++];
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 9;
        Circular<String> circular = new Circular<>(size);
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Entering  " + size + " words...");

        Thread threadPut = new Thread(() ->{
            for (int i = 0; i < circular.totalSize; i++){
                try {
                    circular.put("TestString" + i);
                    //circular.put(scanner.nextLine());
                    System.out.println("added...");

                } catch (InterruptedException e){
                    e.getMessage();
                }
            }
        });

        Thread threadTake = new Thread(() ->{
            for (int i = 0; i < 3; i++){
                try {
                    circular.take();
                    System.out.println("removed...");
                } catch (InterruptedException e){
                    e.getMessage();
                }
            }
        });

        threadTake.start();
        threadPut.start();

        threadPut.join();
        threadTake.join();

        System.out.println("\nYour Strings: ");
        for(String s : circular) {
            System.out.println(s);
        }
        System.out.print("\nYour Queues data: ");
        System.out.println(circular.toString());

        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Circular<String> newCircular = null;

        System.out.println("Serializing data...");

        try{
            outputStream = new ObjectOutputStream(new FileOutputStream("CircularQueue.ser"));
            outputStream.writeObject(circular);

        } catch (IOException e) {
            e.getMessage();
        } finally {

            try{
                if(outputStream != null) {
                    outputStream.close();
                }
            }catch (IOException e){
                e.getMessage();
            }
        }

        System.out.println("\nData after serializing:");
        try{
            inputStream = new ObjectInputStream(new FileInputStream("CircularQueue.ser"));
            newCircular = (Circular<String>) inputStream.readObject();
            System.out.println(newCircular.toString());
        } catch (IOException e){
            e.getMessage();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try{
                if(inputStream != null) {
                    inputStream.close();
                }
            }catch (IOException e){
                e.getMessage();
            }
        }


    }

    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }


    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }


    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }


    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int drainTo(Collection c) {
        return 0;
    }

    @Override
    public int drainTo(Collection c, int maxElements) {
        return 0;
    }


}