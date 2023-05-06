package com.example.firstdraftspringfinalproject.data;

import java.util.HashMap;
import java.util.List;

public interface ChartDataPopulator {


    HashMap<String, Integer> loadXyValuesFromList(List<String> dataList);
}
