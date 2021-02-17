package com.napier.sem;


import java.sql.*;
import java.util.ArrayList;

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
        //Employee emp = a.getEmployee(255530);
        //Display results
        //a.displayEmployee(emp);

        // Extract Employee salary information
        ArrayList<Employee> employees = a.getAllSalaries();

        // Test the size of the returned data - should be 240124
        System.out.println(employees.size());
        // Print out all employee salaries
        a.printSalaries(employees);

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
    public Employee getEmployee(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT e1.emp_no, e1.first_name, e1.last_name, titles.title, salaries.salary, " +
                    "dp1.dept_name, e2.first_name as manager_firstname, e2.last_name as manager_lastname " +
                    "FROM employees e1 JOIN titles ON titles.emp_no = e1.emp_no " +
                    "JOIN dept_emp ON dept_emp.emp_no = e1.emp_no " +
                    "JOIN departments dp1 ON dp1.dept_no = dept_emp.dept_no " +
                    "JOIN dept_manager dm1 ON dm1.dept_no = dp1.dept_no " +
                    "JOIN salaries ON salaries.emp_no = e1.emp_no JOIN employees e2 ON e2.emp_no IN " +
                    "(SELECT dm2.emp_no FROM dept_manager dm2 WHERE dm2.dept_no = dp1.dept_no AND dm2.to_date = '9999-01-01') " +
                    "WHERE dept_emp.emp_no = '" + ID + "' AND salaries.to_date = '9999-1-1' AND dm1.to_date = '9999-1-1' " +
                    "AND titles.to_date = '9999-1-1' AND dept_emp.to_date = '9999-1-1';" ;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid
            // Check one is returned
            if (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset.getString("titles.title");
                emp.salary = rset.getInt("salaries.salary");
                emp.dept_name = rset.getString("dp1.dept_name");
                emp.manager = rset.getString("manager_firstname") + " " + rset.getString("manager_lastname");
                ;
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

    /**
     *  Get all current employees and their salaries
     * @return a list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries()
    {
        try{
            // Create an SQL statement
            Statement statement = con.createStatement();
            // Create a string for the SQL statement
            String strSelect =
                    " SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                    + " FROM employees, salaries "
                    + " WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                    + " ORDER BY employees.emp_no ASC";
            // Execute SQL statement
            ResultSet rset = statement.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Employee salary details.");
            return null;
        }
    }

    /**
     *  Prints a list of all current employees.
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees)
    {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the array list
        for (Employee emp : employees)
        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }
}