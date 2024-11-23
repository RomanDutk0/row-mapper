package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {

        return new RowMapper<Employee>() {
            @Override
            public Employee mapRow(ResultSet resultSet) {

                BigInteger id = null;
                try {
                    id = BigInteger.valueOf(resultSet.getLong("ID"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String firstName = null;
                try {
                    firstName = resultSet.getString("FIRSTNAME");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String lastName = null;
                try {
                    lastName = resultSet.getString("LASTNAME");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String middleName = null;
                try {
                    middleName = resultSet.getString("MIDDLENAME");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Position position = null;
                try {
                    position = Position.valueOf(resultSet.getString("POSITION").toUpperCase());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                LocalDate hired = null;
                try {
                    hired = resultSet.getDate("HIREDATE").toLocalDate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                BigDecimal salary = null;
                try {
                    salary = resultSet.getBigDecimal("SALARY");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                FullName fullName = new FullName(firstName, lastName, middleName);

                return new Employee(id, fullName, position, hired, salary);
            }
        };
    }
}
