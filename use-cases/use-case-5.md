# USE CASE: 5 Add a new employee's details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains all current employees.

### Success End Condition

The new employee's details are added to the database.

### Failed End Condition

The new employee's details are not added to the database.

### Primary Actor

HR Advisor.

### Trigger

A request to add a new employee's details is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance request that new employee's details are added to the database.
2. HR advisor queries the database to add the new employee's details to the database.
3. The query to add the new employee's details passes.

## EXTENSIONS

3. **Employee details already exist**:
    1. HR advisor informs finance that employee is already in the database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0
