public class ArrayDeque<T> {
    T[] items;
    int size;
    int  capacity;
    public ArrayDeque()
    {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 0;
    }

    public ArrayDeque(int num)
    {
        items =(T[]) new Object[num];
        size = 0;
        capacity =  num;
    }
    public  void addFirst(T item)
    {

        for(int i = size; i > 0; i--){
            items[i] = items[i-1];
        }
        items[0] = item;
        size +=1;
    }
    public void addLast(T item)
    {
        items[size] = item;
        size +=1;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public int size()
    {
        return  size;
    }
    public void printDeque()
    {
        for(int i =0; i < size; i++)
        {
            System.out.print(items[i]);
        }
    }
    public T removeFirst()
    {
        size--;
        resize();
        T ans = items[0];
        for(int i = 0; i < size; i++)
        {
            items[i] = items[i+1];
        }
        return ans;
    }
    public T removeLast()
    {
        T ans = items[size];
        size--;
        return ans;
    }
    public T get(int index)
    {
        return items[index -1];
    }
    public void resize()
    {
        int newcapacity = capacity;

        if (size >= capacity)
        {
           newcapacity = 2 * capacity;
        }
        else if(capacity > 1 && size <= capacity / 4)
        {
            newcapacity = capacity / 2;
        }
        else return;

        T[] newArray = (T[]) new Object[newcapacity];
        System.arraycopy(items, 0, newArray, 0, size);

        items = newArray;
        capacity = newcapacity;
    }
}
