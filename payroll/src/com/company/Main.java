package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author buithingocanh
 */
public class Main {

    public static void main(String[] args) {
        String csvFileLink = "../payroll/input/employee.csv";

        List<Employee> list = readFileCSV(csvFileLink);
        for (Employee emp : list) {
            Map<String, Integer> mapYearAndMonth = emp.getWorkingDays(emp.getStartDate());
            System.out.println("Employee{" +
                    "name:'" + emp.getName() + '\'' +
                    ", age:'" + emp.getAge(emp.getDob()) + '\'' +
                    ", rol:'" + emp.getRol() + '\'' +
                    ", workingDay:'" + mapYearAndMonth.get("year") + " years " + mapYearAndMonth.get("month") + " months" + '\'' +
                    ", startSal='" + emp.getStartSal() + '\'' +
                    '}');
        }
    }

    /**
     * method read file .csv
     * 
     * @param csvFile - link file
     * @return data in file
     */
    private static List<Employee> readFileCSV(String csvFile) {
        BufferedReader dataBufferReader = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Employee> employees = new ArrayList<Employee>();
        try {
            dataBufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            while ((line = dataBufferReader.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                employees.add(new Employee(data[0], data[1], data[2], data[3], data[4]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataBufferReader != null) {
                try {
                    dataBufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return employees;
    }

}
