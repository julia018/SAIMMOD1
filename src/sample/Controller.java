package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import sample.logic.LemerGenerator;
import sample.logic.SequenceHandler;

import java.util.Arrays;
import java.util.List;

public class Controller {


    @FXML
    private BarChart<String, Number> barChart;

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
        /*CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Интервал");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Значение плотности");

        // Create a BarChart
        //barChart = new BarChart<String, Number>(xAxis, yAxis);

        // Series 1 - Data of 2014
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data<String, Number>("0.5 - 0.6",0.973));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("0.55", 4.429));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("0.65", 2.792));
        barChart.getData().add(dataSeries1);*/

        LemerGenerator lg = new LemerGenerator(100, 3, 5, 1);
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

        //check aperiodicity segment
        LemerGenerator lg2 = new LemerGenerator(period, 3, 5, 1);
        List<Float> sequenceOfPeriod = lg.generateSequence();
        sh.getAperiodicitySegment(sequence1);

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Интервал");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Значение плотности");

        //barChart = new BarChart<String, Number>(xAxis, yAxis);

        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        //dataSeries1.setName("2014");

        for(int i = 0; i < 20; i++) {
            // Series 1 - Data of 2014

            dataSeries1.getData().add(new XYChart.Data<String, Number>(String.valueOf(sh.getMinValue() + i * sh.getStep()),densityList.get(i)));

        }
        barChart.getData().add(dataSeries1);

    }
}
