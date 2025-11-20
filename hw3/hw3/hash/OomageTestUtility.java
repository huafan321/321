package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int N = oomages.size();
        int tableDemo[] = new int[M];
        double up = N / 2.5;
        double down = N / 50.0;
        for (Oomage tmp : oomages){
            int index = (tmp.hashCode() & 0x7FFFFFFF) % M;
            tableDemo[index] += 1;
        }
        for(int i = 0; i < tableDemo.length; i++){
            if (tableDemo[i] < down || tableDemo[i] > up) return false;
        }
        return true;
    }
}
