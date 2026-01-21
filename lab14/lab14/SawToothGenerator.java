package lab14;

import lab14lib.Generator;
import lab14lib.GeneratorPlayer;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int per){
        period = per;
        state = 0;
    }
    public double next(){
        state += 1;
        return 2.0 / period * (state % period) - 1.0;
    }
}
