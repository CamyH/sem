package com.napier.sem;

import java.sql.*;

/**
 * Class for connecting to and disconnecting from an SQL Database
 */

public class App
{
    // Connection to MySQL Database
    private Connection con = null;

    public static void main(String[] args)
    {
        App a = new App();

        // Connect to Database
        a.connect();

        // Get Employee
        Employee emp = a.getEmployee(255530);
        //Display results
        a.displayEmployee(emp);

        // Disconnect from Database
        a.disconnect();
    }

    // Connect to MySQL Database
    public void connect()
    {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait
                Thread.sleep(10000);
                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    // Disconnect from MySQL Database
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close db connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Get Employee Data
    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT employees.emp_no, first_name, last_name, salaries.salary "
                    + "FROM employees, salaries "
                    + "WHERE employees.emp_no = salaries.emp_no AND employees.emp_no = " + ID;

            // Execute SQL Statement
            ResultSet result = statement.executeQuery(strSelect);
            // Return new employee if valid
            // Check one is returned
            if (result.next())
            {
                Employee emp = new Employee();
                emp.emp_no = result.getInt("emp_no");
                emp.first_name = result.getString("first_name");
                emp.last_name = result.getString("last_name");
                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    // Display Employee information
    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                    + emp.first_name + " "
                    + emp.last_name + "\n"
                    + emp.title + "\n"
                    + "Salary: " + emp.salary + "\n"
                    + emp.dept_name + "\n"
                    + "Manager: " + emp.manager + "\n");
        }
    }

}