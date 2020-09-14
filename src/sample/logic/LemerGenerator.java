package sample.logic;

import java.util.ArrayList;
import java.util.List;

public class LemerGenerator {

    private Integer n;
    private Integer a;
    private Integer m;
    private Integer r0;
    private float sum;


    public LemerGenerator(Integer n, Integer a, Integer m, Integer r0) {
        this.n = n;
        this.a = a;
        this.m = m;
        this.r0 = r0;
    }

    public List<Float> generateSequence() {
        sum = 0;
        List<Float> result = new ArrayList<>();
        float prevX = r0 / (float)m;
        for(int i = 0; i < n; i++) {
            float next = generateNextNumber(prevX);
            result.add(next);
            prevX = next;
            sum += next;
        }
        return result;
    }

    private float generateNextNumber(float prevX) {
        //System.out.println("prevx" + prevX);
        int prevR = (int)Math.ceil(prevX * m);
        //System.out.println("prevR" + prevR);
        int currR = (a * prevR) % m;
        return currR / (float)m;
    }
}
