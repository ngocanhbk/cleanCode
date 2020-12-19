package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author buithingocanh
 */
public class Main {
    final static double COEFFICIENTS_SALARY = 0.06;

    public static void main(String[] args) throws IOException, ParseException {
        String csvFileLink = "../payroll/input/employee.csv";

        List<Employee> list = readFileCSV(csvFileLink);
        for (Employee emp : list) {
            Map<String, Integer> mapYearAndMonth = emp.getWorkingDays(emp.getStartDate());
            long nowSal = calculateSalary(Long.valueOf(emp.getStartSal()), mapYearAndMonth.get("year"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date d = sdf.parse(emp.getDob());
            sdf.applyPattern("yyyy-MM-dd");
            String birthday = sdf.format(d);
            System.out.println("Employee {" +
                    "name:'" + emp.getName() + '\'' +
                    ", birthday:'" + birthday + '\'' +
                    ", age:'" + emp.getAge(emp.getDob()) + '\'' +
                    ", rol:'" + emp.getRol() + '\'' +
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

    /**
     * function calculate salary
     * @param startSalary - begin salary
     * @param yearWorkings - year worked
     * @return now salary
     */
    private static long calculateSalary(long startSalary, int yearWorkings) {
        return (long) (Long.valueOf(startSalary) * Math.pow((1 + COEFFICIENTS_SALARY), yearWorkings));
    }


}
