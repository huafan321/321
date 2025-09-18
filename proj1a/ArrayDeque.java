public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private  int front;
    private  int last;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        last = 0;
    }

    public  void addFirst(T item) {

        items[minusOne(front)] = item;
        front = minusOne(front);
        size++;
        resize();
    }
    public void addLast(T item) {
        items[last] = item;
        last = addOne(last);
        size++;
        resize();
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return  size;
    }
    public void printDeque() {
        int i = front;
        while(addOne(i) != last)
        {
            System.out.println(items[i]);
            i+=1;
        }
    }
    public T removeFirst() {
        if (size <= 0)
        {
            return null;
        }

        T ans = items[front];
        items[front] = null;
        front = addOne(front);
        size--;
        resize();
        return ans;
    }
    public T removeLast() {
        if (size <= 0)
        {
            return null;
        }
        last = minusOne(last);
        T ans = items[last];
        items[last] = null;
        size--;
        resize();
        return ans;
    }
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(index + front) % items.length];
    }
    private void resize() {
        if (size == items.length)
        {
            T[] a = (T[]) new Object[items.length * 2];
            for (int i =0; i < size; i++)
            {
                int oldIndex = (front + i) % items.length;
                a[i] = items[oldIndex];
            }
            items = a;
            front = 0;
            last = size;
        }
        else if ((size < items.length/4) && (items.length >= 16))
        {
            T[] a = (T[]) new Object[items.length / 2];
            for (int i =0; i < size; i++)
            {
                int oldIndex = (front + i) % items.length;
                a[i] = items[oldIndex];
            }
            items = a;
            front = 0;
            last = size;
        }
    }
    private int minusOne(int index)
    {
        return  (index - 1 + items.length) % items.length;
    }
    private int addOne(int index)
    {
        return  (index + 1 + items.length) % items.length;
    }
}
