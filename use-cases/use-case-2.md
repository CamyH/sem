# USE CASE: 2 Produce a report on the salary of Employees in a Department

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on the salary of employees in a department* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains all current employee salary data and departments.

### Success End Condition

A report is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for employee salary information in a department is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance request employee salary information in a department.
2. HR advisor extracts current salary information of employees in that department from the database.
3. HR advisor provides report to finance.

## EXTENSIONS

3. **Department does not exist**:
    1. HR advisor informs finance that department does not exist.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
