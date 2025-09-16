public class LinkedListDeque<T> {
    public class TNode {
        private T item;
        private TNode pre;
        private TNode next;

        public TNode() { }
        public TNode(T i, TNode n) {
            item = i;
            next = n;
        }
    }

    private int size;
    private TNode sentinel;

    public  LinkedListDeque(){
        size = 0;
        sentinel = new TNode();
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
    }

    public  void addFirst(T item){
        size += 1;
        TNode tmp = new TNode(item,sentinel.next);
        sentinel.next.pre = tmp;
        sentinel.next = tmp;
        tmp.pre = sentinel;
    }
    public void addLast(T item)
    {
        size += 1;
        TNode tmp = new TNode(item,sentinel);
        tmp.pre = sentinel.pre;
        sentinel.pre.next = tmp;
        sentinel.pre = tmp;

    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public int size()
    {
        return size;
    }
    public void printDeque()
    {
        TNode ptr = sentinel.next;
        while(ptr != sentinel)
        {
            System.out.println(ptr.item);
            ptr = ptr.next;
        }
    }
    public T removeFirst()
    {
        size--;
        T ans = sentinel.next.item;
        TNode g = sentinel.next;

        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;

        g.pre = null;
        g.next = null;

        return ans;
    }
    public T removeLast()
    {
        if (size <= 0)
        {
            return null;
        }
        size--;
        T ans = sentinel.pre.item;
        TNode g = sentinel.pre;

        g.pre.next = g.next;
        sentinel.pre = g.pre;

        g.pre =null;
        g.next = null;
        return ans;
    }
    public T get(int index)
    {
        int i = 0;
        TNode ptr = sentinel.next;
        while(i < index)
        {
            ptr = ptr.next;
            i += 1;
        }
        return ptr.item;
    }

    public  T getRecursive(int index)
    {
        if (index <= 0 || index >= size)
        {
            return sentinel.next.item;
        }
        return helper(sentinel.next,index);
    }

    private T helper (TNode node, int index)
    {
        if (index == 0)
        {
            return node.item;
        }
        return helper(node.next,index-1);
    }
}
