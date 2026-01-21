package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int per){
        period = per;
        state = 0;
    }
    public double next(){
        state += 1;
        int weirdState = state & (state >> 7) % period;
        return 2.0 / period * (weirdState % period) - 1.0;
    }
}
