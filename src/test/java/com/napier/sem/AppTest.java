package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    // Print Salaries Unit Tests
    @Test
    void printSalariesTestNull()
    {
        app.printSalaries(null);
    }

    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(null);
        app.printSalaries(employees);
    }

    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Cameron";
        emp.last_name = "Hunt";
        emp.title = "Engineer";
        emp.salary = 75000;
        employees.add(emp);
        app.printSalaries(employees);
    }

    // Display Employee Unit Tests
    @Test
    void displayEmployeeTestNull()
    {
        app.displayEmployee(null);
    }

    @Test
    void displayEmployeeTestContainsNull()
    {
        Employee emp;
        emp = null;
        app.displayEmployee(emp);
    }
}