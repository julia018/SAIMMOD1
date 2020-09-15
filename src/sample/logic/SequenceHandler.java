package sample.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequenceHandler {

    private final int INTERVAL_AMOUNT = 20;

    private float step;
    private float minValue;
    private float maxValue;


    public List<Float> getDensityList(List<Float> sequence) {
        List<Float> densityList = new ArrayList<>();
        for (int i = 0; i < INTERVAL_AMOUNT; i++) {
            densityList.add((float) 0);
        }
        float intervalMinValue = minValue;

        // a <= x < b
        for (float num : sequence) {
            for (int i = 0; i < INTERVAL_AMOUNT; i++) {
                float intervalStart = intervalMinValue + step * i;
                float intervalEnd = intervalStart + step;
                if ((num >= intervalStart) && (num < intervalEnd)) {
                    float prev = densityList.get(i);
                    densityList.set(i, prev + 1);
                    break;
                }
            }
        }

        for (int i = 0; i < INTERVAL_AMOUNT; i++) {
            float amount = densityList.get(i);
            densityList.set(i, amount / sequence.size());
        }

        return densityList;
    }

    public float countM(float sum, int n) {
        return sum / n;
    }

    public float countD(List<Float> sequenceList, float M) {
        float sum = 0;
        for (Float number : sequenceList) {
            sum += (number - M) * (number - M);
        }
        return sum / sequenceList.size();
    }

    public float countAverageD(float D) {
        return (float) Math.sqrt(D);
    }

    public float getStep() {
        return step;
    }

    public void setSequenceSpecs(List<Float> sequence) {
        this.minValue = Collections.min(sequence);
        this.maxValue = Collections.max(sequence);
        this.step = (maxValue - minValue) / INTERVAL_AMOUNT;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public double checkSequence(List<Float> sequence) {
        int k = 0;
        for (int i = 0; i < sequence.size(); i += 2) {
            double sum = Math.pow(sequence.get(i), 2) + Math.pow(sequence.get(i + 1), 2);
            if (Double.compare(sum, 1) < 0) {
                k++;
            }
        }
        return (double) 2 * k / sequence.size();
    }

    public int getPeriod(List<Float> sequence) {
        Float xV = sequence.get(sequence.size() - 1);
        List<Integer> matchingIndices = new ArrayList<>();
        for (int i = 0; i < sequence.size(); i++) {
            if (sequence.get(i).equals(xV)) {
                matchingIndices.add(i);
            }
        }
        System.out.println(matchingIndices.toString());
        return matchingIndices.get(1) - matchingIndices.get(0);
    }

    public int getAperiodicitySegment(List<Float> sequence) {
        int period = getPeriod(sequence);
        for (int i = 0; i < sequence.size() - period; i++) {
            if (sequence.get(i + period).equals(sequence.get(i))) {
                System.out.println(i);
                return i + period;
            }
        }
        return -1;
    }
}
