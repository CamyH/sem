# USE CASE: 3 Produce a report on the salary of Employees in my Department 

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Department Manager* I want *to produce a report on the salary of employees in my department* so that *I can support financial reporting for my department.*

### Scope

Department.

### Level

Primary task.

### Preconditions

Database contains all current employee salary data for all departments.

### Success End Condition

A report is available for the Department Manager to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Trigger

A request for employee salary information is sent to the Department Manager.

## MAIN SUCCESS SCENARIO

1. Finance request employee salary information for a specific department.
2. Department Manager extracts current salary information of employees in their department from the database.
3. Department Manager provides report to finance.

## EXTENSIONS

3. **Department does not exist**:
    1. HR advisor informs finance that department does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
