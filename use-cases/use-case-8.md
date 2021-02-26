# USE CASE: 8 Delete an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains current employee details.

### Success End Condition

The employee's details are successfully deleted.

### Failed End Condition

The employee's details are not deleted.

### Primary Actor

HR Advisor.

### Trigger

An employee leaves the company.

## MAIN SUCCESS SCENARIO

1. An employee leaves the company.
2. HR advisor queries the database to delete the ex-employee's details.
3. The ex-employee's details are deleted.

## EXTENSIONS

None.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
