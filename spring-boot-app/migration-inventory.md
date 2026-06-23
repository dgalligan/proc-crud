# Migration Inventory

## Source Files Analyzed
- `src/app.pc` - Main application with CRUD operations on `emp_test` table
- `src/db.pc` - Database connection/disconnection management

## Tables Identified
| Table | Columns | Primary Key |
|-------|---------|-------------|
| emp_test | empno (int), ename (char[21]), sal (float) | empno |

## Host Variables
| File | Variable | C Type | Java Type |
|------|----------|--------|-----------|
| app.pc | h_empno | int | Integer |
| app.pc | h_ename | char[21] | String |
| app.pc | h_sal | float | BigDecimal |
| db.pc | username | char[30] | (externalized config) |
| db.pc | password | char[30] | (externalized config) |

## EXEC SQL Statements
| File | Function | Statement Type | Mapped To |
|------|----------|---------------|-----------|
| app.pc | read_data | SELECT | EmpTestService.findById() |
| app.pc | insert_data | INSERT | EmpTestService.create() |
| app.pc | update_data | UPDATE | EmpTestService.update() |
| app.pc | delete_data | DELETE | EmpTestService.delete() |
| app.pc | insert_data | COMMIT | @Transactional |
| app.pc | insert_data | ROLLBACK | @Transactional (auto) |
| app.pc | update_data | COMMIT | @Transactional |
| app.pc | update_data | ROLLBACK | @Transactional (auto) |
| app.pc | delete_data | COMMIT | @Transactional |
| app.pc | delete_data | ROLLBACK | @Transactional (auto) |
| db.pc | db_connect | CONNECT | Spring DataSource config |
| db.pc | db_disconnect | COMMIT WORK RELEASE | Spring connection pool |

## Error Handling Patterns
| Pattern | Pro*C Code | Java Equivalent |
|---------|-----------|-----------------|
| No data found | sqlca.sqlcode == 1403 | ResourceNotFoundException |
| General error | sqlca.sqlcode != 0 | Spring DataAccessException |

## Advanced Constructs
- No cursors found
- No dynamic SQL found
- No stored procedure calls found
- No PL/SQL anonymous blocks found
