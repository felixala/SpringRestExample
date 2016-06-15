package com.felixlaura.dao;

import java.util.List;

import com.felixlaura.entity.Employee;

public interface EmployeeDao {
	
	List<Employee> getEmployees();
	Employee getEmployee(Long employeeId);
	int deleteEmployee(Long employeeId);
	int createEmployee(Employee employee);
	int updateEmployee(Employee employee);

}
