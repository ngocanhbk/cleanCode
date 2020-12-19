package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author buithingocanh
 */
public class Main {
    final static double COEFFICIENTS_SALARY = 1.06;

    public static void main(String[] args) throws IOException {
        String csvFileLink = "../payroll/input/employee.csv";

        List<Employee> list = readFileCSV(csvFileLink);
        for (Employee emp : list) {
            Map<String, Integer> mapYearAndMonth = emp.getWorkingDays(emp.getStartDate());
            long nowSal = (long) (Long.valueOf(emp.getStartSal()) * Math.pow(COEFFICIENTS_SALARY, mapYearAndMonth.get("year")));
            System.out.println("Employee {" +
                    "name:'" + emp.getName() + '\'' +
//                    ", birthday:'" + emp.getDob() + '\'' +
                    ", age:'" + emp.getAge(emp.getDob()) + '\'' +
//                    ", rol:'" + emp.getRol() + '\'' +
                    ", workingDay:'" + mapYearAndMonth.get("year") + " years " + mapYearAndMonth.get("month") + " months" + '\'' +
                    ", startSal='" + emp.getStartSal() + '\'' +
                    ", nowSal='" + nowSal + '\'' +
                    '}');
        }
    }

    /**
     * method read file .csv
     *
     * @param csvFile - link file
     * @return data in file
     */
    private static List<Employee> readFileCSV(String csvFile) throws FileNotFoundException, IOException {
        BufferedReader dataBufferReader = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Employee> employees = new ArrayList<>();
        try {
            dataBufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8));
            while ((line = dataBufferReader.readLine()) != null) {
                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                employees.add(new Employee(data[0], data[1], data[2], data[3], data[4]));
            }
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
