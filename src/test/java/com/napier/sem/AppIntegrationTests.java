package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertEquals(emp.emp_no, 255530);
        assertEquals(emp.first_name, "Ronghao");
        assertEquals(emp.last_name, "Garigliano");
    }

    @Test
    void testGetAllSalaries()
    {
        ArrayList<Employee> employees = app.getAllSalaries();
        assertEquals(employees.size(), 240124);
    }

    @Test
    void testGetAllSalariesByRole()
    {
        //ArrayList<Employee> salaries = app.getAllSalariesByRole("Sales");
        //assertEquals(salaries.size(), 240124);
    }

    @Test
    void testGetDepartment()
    {

    }

    @Test
    void testGetSalariesByDepartment()
    {

    }
}