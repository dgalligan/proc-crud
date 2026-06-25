# Migration Inventory

## Source Files Analyzed

### app.pc
- **Host Variables**: `h_empno` (int), `h_ename` (char[21]), `h_sal` (float)
- **Tables Referenced**: `emp_test`
- **Columns Referenced**: `empno`, `ename`, `sal`
- **EXEC SQL Statements**:
  - SELECT INTO (read_data)
  - INSERT INTO (insert_data)
  - UPDATE (update_data)
  - DELETE FROM (delete_data)
  - COMMIT / ROLLBACK
- **Error Handling**: sqlca.sqlcode checks (0 = success, 100/1403 = no data found)
- **Cursors**: None
- **Dynamic SQL**: None
- **Stored Procedures**: None

### db.pc
- **Host Variables**: `username` (char[30]), `password` (char[30])
- **EXEC SQL Statements**:
  - CONNECT :username IDENTIFIED BY :password
  - COMMIT WORK RELEASE
- **Error Handling**: sqlca.sqlcode != 0

## Table Schema

| Table     | Column | C Type   | Java Type   | JPA Annotation |
|-----------|--------|----------|-------------|----------------|
| emp_test  | empno  | int      | Integer     | @Id            |
| emp_test  | ename  | char[21] | String      | @Column(length=20) |
| emp_test  | sal    | float    | BigDecimal  | @Column        |

## Function to Service Method Mapping

| Pro*C Function | Service Method | HTTP Endpoint | HTTP Method |
|---------------|---------------|---------------|-------------|
| insert_data   | create        | /api/employees | POST       |
| read_data     | findById      | /api/employees/{empno} | GET |
| update_data   | update        | /api/employees/{empno} | PUT |
| delete_data   | delete        | /api/employees/{empno} | DELETE |

## Advanced Constructs
- No cursors found
- No dynamic SQL found
- No stored procedure calls found
- No PL/SQL anonymous blocks found
