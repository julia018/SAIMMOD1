package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import sample.logic.LemerGenerator;
import sample.logic.SequenceHandler;

import java.util.Arrays;
import java.util.List;

public class Controller {


    @FXML
    private AreaChart<Number, Number> barChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button buildButton;

    @FXML
    private Button generateButton;

    @FXML
    void buildBarChart(ActionEvent event) {

    }

    @FXML
    void generateSequence(ActionEvent event) {

    }

    @FXML
    void initialize() {
        configureBarChart();
    }

    private void configureBarChart() {

        LemerGenerator lg = new LemerGenerator(1000, 3, 5000 , 1);
        List<Float> sequence = lg.generateSequence();
        SequenceHandler sh = new SequenceHandler();
        sh.setSequenceSpecs(sequence);
        List<Float> densityList = sh.getDensityList(sequence);
        double k = sh.checkSequence(sequence);
        System.out.println(k);
        System.out.println(Arrays.toString(sequence.toArray()));
        System.out.println(Arrays.toString(densityList.toArray()));

        //check period
        LemerGenerator lg1 = new LemerGenerator(Integer.MAX_VALUE, 3, 5, 1);
        List<Float> sequence1 = lg.generateSequence();
        int period = sh.getPeriod(sequence1);
        System.out.println("period " + period);
        System.out.println("aper segment " + sh.getAperiodicitySegment(sequence));

        //check aperiodicity segment
        LemerGenerator lg2 = new LemerGenerator(period, 3, 5, 1);
        List<Float> sequenceOfPeriod = lg.generateSequence();
        sh.getAperiodicitySegment(sequence1);



        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(sh.getMinValue());
        xAxis.setUpperBound(sh.getMaxValue());
        xAxis.setTickUnit(sh.getStep());

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(1);
        yAxis.setTickUnit(0.05);


        //barChart = new BarChart<String, Number>(xAxis, yAxis);

        /*XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        //dataSeries1.setName("2014");

        for(int i = 0; i < 20; i++) {
            // Series 1 - Data of 2014

            dataSeries1.getData().add(new XYChart.Data<String, Number>(String.valueOf(sh.getMinValue() + i * sh.getStep()),densityList.get(i)));

        }
        barChart.getData().add(dataSeries1);*/

        XYChart.Series seriesDensity = new XYChart.Series();

        for(int i = 0; i < 20; i ++) {
            seriesDensity.getData().add(new XYChart.Data(sh.getMinValue() + i * sh.getStep(), densityList.get(i)));
            seriesDensity.getData().add(new XYChart.Data(sh.getMinValue() + (i + 1) * sh.getStep(), densityList.get(i)));
        }

        barChart.getData().add(seriesDensity);

    }
}
