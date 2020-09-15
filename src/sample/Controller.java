package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField aField;

    @FXML
    private TextField mField;

    @FXML
    private TextField roField;

    @FXML
    private TextField expectedValue;

    @FXML
    private TextField dispersion;

    @FXML
    private TextField deviation;

    @FXML
    private TextField indirectEst;

    @FXML
    private TextField period;

    @FXML
    private TextField aperiodicitySection;

    public static LemerGenerator lg;





    @FXML
    void generateSequence(ActionEvent event) {
        if (aField.getText().equals("") && mField.getText().equals("") && roField.getText().equals("")) {
            return;
        }
        lg = new LemerGenerator(50000, Integer.parseInt(aField.getText()), Integer.parseInt(mField.getText()), Float.parseFloat(roField.getText()));
        System.out.println(Arrays.toString(lg.generateSequence().toArray()));
    }

    @FXML
    void buildBarChart(ActionEvent event) {

        SequenceHandler sh = new SequenceHandler();
        sh.setSequenceSpecs(lg.getSequence());
        List<Float> densityList = sh.getDensityList(lg.getSequence());

        //draw chart
        configureBarChart(sh, densityList);

        // count estimations
        float M = sh.countM(lg.getSum(), lg.getSequence().size());
        System.out.println("M" + M);
        expectedValue.setText(Float.toString(M));
        float D = sh.countD(lg.getSequence(), M);
        System.out.println("D" + D);
        dispersion.setText(Float.toString(D));
        deviation.setText(Float.toString(sh.countAverageD(D)));
        double k = sh.checkSequence(lg.getSequence());
        indirectEst.setText(Double.toString(k));

        period.setText(Integer.toString(sh.getPeriod(lg.getSequence())));
        aperiodicitySection.setText(Integer.toString(sh.getAperiodicitySegment(lg.getSequence())));
    }

    private void configureBarChart(SequenceHandler sh, List<Float> densityList) {

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(sh.getMinValue());
        xAxis.setUpperBound(sh.getMaxValue());
        xAxis.setTickUnit(sh.getStep());

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(1);
        yAxis.setTickUnit(0.05);

        XYChart.Series seriesDensity = new XYChart.Series();

        seriesDensity.setName("частота");

        for(int i = 0; i < 20; i ++) {
            seriesDensity.getData().add(new XYChart.Data(sh.getMinValue() + i * sh.getStep(), densityList.get(i)));
            seriesDensity.getData().add(new XYChart.Data(sh.getMinValue() + (i + 1) * sh.getStep(), densityList.get(i)));
        }

        barChart.getData().add(seriesDensity);

    }
}
