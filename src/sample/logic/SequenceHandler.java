package sample.logic;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SequenceHandler {

    private final int INTERVAL_AMOUNT = 20;

    private float step;
    private float minValue;
    private float maxValue;


    public List<Float> getDensityList(List<Float> sequence) {
        List<Float> densityList = new ArrayList<>();
        for(int i = 0; i < INTERVAL_AMOUNT; i++) {
            densityList.add((float)0);
        }
        float intervalMinValue = minValue;

        // a <= x < b
        for(float num: sequence) {
            for(int i = 0; i < INTERVAL_AMOUNT; i++) {
                float intervalStart = intervalMinValue + step * i;
                float intervalEnd = intervalStart + step;
                if((num >= intervalStart) && (num < intervalEnd)) {
                    float prev = densityList.get(i);
                    densityList.set(i, prev + 1);
                    break;
                }
            }
        }

        for(int i = 0; i < INTERVAL_AMOUNT; i++) {
            float amount = densityList.get(i);
            densityList.set(i, amount / sequence.size());
        }

        return densityList;
    }

    private float countM(float sum, int n) {
        return sum / n;
    }

    private float countD(ArrayList<Float> sequenceList, float M) {
        float sum = 0;
        for(Float number: sequenceList) {
            sum += (number - M) * (number - M);
        }
        return sum / sequenceList.size();
    }

    private float countAverageD(float D) {
        return (float) Math.sqrt(D);
    }

    public float getStep() {
        return step;
    }

    public void setSequenceSpecs(List<Float> sequence) {
        this.minValue = Collections.min(sequence);
        this.maxValue = Collections.max(sequence);
        this.step = ( maxValue - minValue) / INTERVAL_AMOUNT;
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}
