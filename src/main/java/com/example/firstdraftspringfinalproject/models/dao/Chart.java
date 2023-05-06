package com.example.firstdraftspringfinalproject.models.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Chart {

    private HashMap<String, Integer> xyValues;

    private String title;

    public HashMap<String, Integer> getXyValues() {
        return xyValues;
    }

    public void setXyValues(HashMap<String, Integer> xyValues) {
        this.xyValues = xyValues;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void populateChartData(){

    }

    public static void main(String[] args)
    {
        String testString1 = "Fox Turner,7";
        String testString2 = "Harry Beckerle,9";
        List<String> testList = new ArrayList<>();
        testList.add(testString1);
        testList.add(testString2);

        Chart.populateChartDataFromListTest(testList);

    }

    public HashMap<String, Integer> populateChartDataFromList(List<String> rawData) {
        HashMap<String, Integer> xyValues = new HashMap<>();
        for (String dataPair:
             rawData) {
            String[] dataPairArray= dataPair.split(",");
            List<String> dataPairList = Arrays.stream(dataPairArray).toList();
            String xValue = dataPairList.get(0);
            String yValueString = dataPairList.get(1);
            Integer yValue = Integer.parseInt(yValueString);
            System.out.println(xValue);
            System.out.println(yValue.getClass());
            System.out.println(yValue);
        }
        return xyValues;
    }

    public static void populateChartDataFromListTest(List<String> rawData) {

        //TODO : a place to use Stream???
        for (String dataPair:
                rawData) {
            String[] dataPairArray= dataPair.split(",");
            List<String> dataPairList = Arrays.stream(dataPairArray).toList();
            String xValue = dataPairList.get(0);
            String yValueString = dataPairList.get(1);
            Integer yValue = Integer.parseInt(yValueString);

            System.out.println(xValue);
            System.out.println(yValue.getClass());
            System.out.println(yValue);
        }
    }
}
