package sample.logic;

public class LemerGenerator {

    private Integer n;
    private Integer a;
    private Integer m;
    private Integer r0;


    public LemerGenerator(Integer n, Integer a, Integer m, Integer r0) {
        this.n = n;
        this.a = a;
        this.m = m;
        this.r0 = r0;
    }

    public float[] generateSequence() {
        float[] result = new float[n];
        float prevX = r0 / (float)m;
        for(int i = 0; i < n; i++) {
            result[i] = generateNextNumber(prevX);
            prevX = result[i];
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
