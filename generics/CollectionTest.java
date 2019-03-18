package generics;
import java.util.*;
import java.lang.*;

public class CollectionTest {

    public static void CollTest(Collection<Integer> collection, int testNr, TimeResult data, int size) {

        Random elements = new Random();
        long timeStart, timeStop;

        timeStart = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            collection.add(elements.nextInt(1000));
        }
        timeStop = System.currentTimeMillis();
        data.add[testNr] = (timeStop - timeStart)/1000.0;

        timeStart = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            collection.contains(elements.nextInt(1000));
        }
        timeStop = System.currentTimeMillis();
        data.contains[testNr] = (((double)timeStop - (double)timeStart)/1000.0);

        timeStart = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            collection.remove(size - i - 1);
        }
        timeStop = System.currentTimeMillis();
        data.remove[testNr] = (((double) timeStop - (double) timeStart)/1000.0);

    }


    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int testNr = 5;
        int num;

        TimeResult arrayList = new TimeResult(testNr);
        TimeResult hashSet = new TimeResult(testNr);
        TimeResult linkedList = new TimeResult(testNr);
        TimeResult stack = new TimeResult(testNr);
        TimeResult vector = new TimeResult(testNr);
        TimeResult priorityQueue = new TimeResult(testNr);
        TimeResult treeSet = new TimeResult(testNr);

        for (int i = 0; i < testNr; i++){
            CollTest(new ArrayList<Integer>(), i, arrayList, size);
            CollTest(new HashSet<Integer>(), i, hashSet, size);
            CollTest(new LinkedList<Integer>(), i, linkedList, size);
            CollTest(new Stack<Integer>(), i, stack, size);
            CollTest(new Vector<>(), i, vector, size);
            CollTest(new PriorityQueue<Integer>(), i, priorityQueue, size);
            CollTest(new TreeSet<Integer>(), i, treeSet, size);
        }

        System.out.println("ArrayList");
        arrayList.show();
        System.out.println("HashSet");
        hashSet.show();
        System.out.println("LinkedList");
        linkedList.show();
        System.out.println("Stack");
        stack.show();
        System.out.println("Vector");
        vector.show();
        System.out.println("PriorityQueue");
        priorityQueue.show();
        System.out.println("TreeSet");
        treeSet.show();
    }

}


