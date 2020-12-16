package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String COMMA_DELIMITER =",";

    public static void main(String[] args) {
        String csvFileLink = "/Users/LA-PM/Desktop/myweb/payroll/input/employee.csv";
        Employee employee = new Employee("ngoc anh","19970923","engineer",	"20200216","8000000");
        System.out.println(employee.getAge(employee.getDob()));
        long day = employee.getWorkingDays(employee.getStartDate());
        System.out.println(day);
        System.out.println("year="+(day/365)+" month="+(day/30 - 12* (day/365)));

        List<Employee> list = readFileCSV(csvFileLink);
        for (Employee emp :list  ) {
            System.out.println(emp.toString());

        }

        // write your code here
    }

    private static List<Employee>  readFileCSV(String csvFile){
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<Employee> employees = new ArrayList<Employee>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] data = line.split(cvsSplitBy);
                employees.add(new Employee(data[0],data[1],data[2],data[3],data[4]));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        return employees;
    }


    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        try {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        } finally {
            rowScanner.close();
        }
        return values;
    }


}
