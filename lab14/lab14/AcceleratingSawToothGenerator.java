package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int state;
    private int period;
    private double factor;
    private  int sum;

    public AcceleratingSawToothGenerator(int period,double factor){
       this.period = period;
       state = 0;
       this.factor = factor;
       sum = 0;
    }
    public double next(){
        state += 1;
        return getK()* (state - sum) - 1.0;
    }

    private double getK(){
        if(state > sum) {
            sum += period;
            period *= factor;
        }
        return 2.0 / period;
    }
}
