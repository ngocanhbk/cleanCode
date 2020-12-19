package com.company.Controller;


import com.company.Domain.Employee;

import java.io.*;
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
            String birthday = convertFormatDate(emp.getDob());
            String startTime = convertFormatDate(emp.getStartDate());
            System.out.println("Employee {" +
                    "name:'" + emp.getName() + '\'' +
                    ", birthday:'" + birthday + '\'' +
                    ", age:'" + emp.getAge(emp.getDob()) + '\'' +
                    ", rol:'" + emp.getRol() + '\'' +
                    ", startTime:'" + startTime + '\'' +
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

    /**
     * conver string from format yyyyMMdd to format yyyy-MM-dd
     * @param oldDateString - old date
     * @return new date
     * @throws ParseException
     */
    private static String convertFormatDate(String oldDateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(oldDateString);
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(date);
    }


}
