import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> q = new Queue<>();
        for(Item t : items){
            Queue<Item> tmp  = new Queue<>();
            tmp.enqueue(t);
            q.enqueue(tmp);
        }
        return q;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> q = new Queue<>();
        int n = q1.size() + q2.size();
        for (int i = 0; i < n; i++) {
           Item tmp = getMin(q1,q2);
           q.enqueue(tmp);
        }
        return q;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> tmp = makeSingleItemQueues(items);
        Queue<Item> q = new Queue<>();
        int n = tmp.size();
        for(int i = 0; i < n; i++){
           q = mergeSortedQueues(q,tmp.dequeue());
        }
        items = q;
        return items;
    }
    public static void main(String agrv[]){
        testNum();
    }

    private static void testString(){
        Queue<String> students = new Queue<>();

        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Jackson");
        students.enqueue("Oner");
        printQueue(students);

        students =  MergeSort.mergeSort(students);
        printQueue(students);

    }
    private static void testNum(){
        Queue<Integer> num = new Queue<>();
        num.enqueue(6);
        num.enqueue(9);
        num.enqueue(8);
        num.enqueue(2);
        num.enqueue(31);
        num.enqueue(40);
        num.enqueue(68);
        num.enqueue(90);
        num.enqueue(0);
        num.enqueue(112);
        num.enqueue(3242);
        printQueue(num);
        num = MergeSort.mergeSort(num);
        printQueue(num);
    }

    private static<T> void printQueue(Queue<T> q){
        if (q.isEmpty()) return;
        for (T element : q) {
            System.out.println(element);
        }

        System.out.println("-----------------------------------------");
    }
}
