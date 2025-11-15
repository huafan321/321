package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }


    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        if(buckets[index].containsKey(key)) return buckets[index].get(key);
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int index = hash(key);
        if (!buckets[index].containsKey(key)){
            buckets[index].put(key,value);
            size += 1;
        }
        else buckets[index].put(key,value);

        if ((double) size / buckets.length > 0.75) {
            resize(buckets.length * 2);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    private void resize(int num){
        ArrayMap<K, V>[] newHash = new ArrayMap[num];
        for (int i = 0; i < num; i++){
            newHash[i] = new ArrayMap<>();
        }

        for (int i =0; i < buckets.length; i++){
            for(K key : buckets[i]){
                V value = buckets[i].get(key);

                int newIndex = hash(key);
                newHash[newIndex].put(key,value);
            }
        }

        buckets = newHash;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet<K> ans = new HashSet<>();
        for (int i = 0; i < buckets.length; i++){
            for(K key : buckets[i].keySet()){
                ans.add(key);
            }
        }
        return ans;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int index = hash(key);
        if(buckets[index].containsKey(key)){
            V value = buckets[index].get(key);
            buckets[index].remove(key);
            return value;
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int index = hash(key);
        if(buckets[index].containsKey(key)){
           if(buckets[index].get(key).equals(value)) {
               buckets[index].remove(key);
               return value;
           }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int bucketIndex = 0;             // 当前桶索引
            private Iterator<K> keyIterator = null;  // 当前桶内部迭代器

            // 找到下一个有元素的桶
            private void advance() {
                while ((keyIterator == null || !keyIterator.hasNext()) && bucketIndex < buckets.length) {
                    keyIterator = buckets[bucketIndex].keySet().iterator();
                    bucketIndex++;
                }
            }

            @Override
            public boolean hasNext() {
                advance();
                return keyIterator != null && keyIterator.hasNext();
            }

            @Override
            public K next() {
                advance();
                return keyIterator.next();
            }
        };
    }
}
