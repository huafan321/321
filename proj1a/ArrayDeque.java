public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private  int front;
    private  int last;

    public ArrayDeque()
    {
        items = (T[]) new Object[8];
        size = 0;
        front = 3;
        last = 3;
    }

    public  void addFirst(T item) {
        resize();
        items[minusOne(front)] = item;
        front = minusOne(front);
        size++;
    }
    public void addLast(T item) {
        resize();
        items[addOne(last)] = item;
        last = addOne(last);
        size++;
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
        resize();
        size--;
        return ans;
    }
    public T removeLast() {
        if (size <= 0)
        {
            return null;
        }
        T ans = items[last];
        items[last] = null;
        last = minusOne(last);
        size--;
        return ans;
    }
    public T get(int index) {
        int i =0;
        int ans = front;
        while(i < index)
        {
            ans = addOne(ans);
            i++;
        }
        return items[ans];
    }
    private void resize() {
        if (size == items.length)
        {
            T[] a = (T[]) new Object[size * 2];
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
            T[] a = (T[]) new Object[size / 2];
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
