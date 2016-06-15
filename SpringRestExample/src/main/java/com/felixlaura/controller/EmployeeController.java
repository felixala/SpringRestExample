package com.felixlaura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.felixlaura.entity.Employee;
import com.felixlaura.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employee", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Employee>> getEmployees(){
		HttpHeaders header = new HttpHeaders();
		List<Employee> employees = employeeService.getEmployees();
		
		if(employees == null){
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}
		header.add("Number of Records Found", String.valueOf(employees.size()));
		return new ResponseEntity<List<Employee>>(employees, header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id){
		Employee employee = employeeService.getEmployee(id);
		HttpHeaders header = new HttpHeaders();
		if(employee == null){
			new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		String fullName = employee.getFirstName() + " " + employee.getLastName();
		header.add("Record Found", fullName);
		return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") Long id){
		Employee employee = employeeService.getEmployee(id);
		HttpHeaders header = new HttpHeaders();
		if(employee == null){
			new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		employeeService.deleteEmployee(id);
		String fullName = employee.getFirstName() + " " + employee.getLastName();
		header.add("Record Deleted", fullName);
		return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		HttpHeaders header = new HttpHeaders();
		if(employee == null){
			new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
		String fullName = employee.getFirstName() + " " + employee.getLastName();
		header.add("Record Created", fullName);
		employeeService.createEmployee(employee);
		return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
		HttpHeaders header = new HttpHeaders();
		Employee ifExist = employeeService.getEmployee(id);
		
		if(ifExist == null){
			new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}else if(employee == null){
			new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		}
		String fullName = employee.getFirstName() + " " + employee.getLastName();
		header.add("Record Updated", fullName);
		employeeService.updateEmployee(employee);
		
		return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
	}
}
