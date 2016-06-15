package com.felixlaura.service;

import java.util.List;

import com.felixlaura.entity.Employee;

public interface EmployeeService {

	List<Employee> getEmployees();
	Employee getEmployee(Long employeeId);
	int deleteEmployee(Long employeeId);
	int createEmployee(Employee employee);
	int updateEmployee(Employee employee);
	
}
