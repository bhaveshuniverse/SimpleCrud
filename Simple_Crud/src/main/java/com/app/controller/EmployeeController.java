package com.app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;

import com.app.pojos.Employee;
import com.app.service.EmployeeService;

@RestController
//To enable CORS header 
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/employees")
public class EmployeeController {
//dep : service layer i.f
	@Autowired
	private EmployeeService empService;

	public EmployeeController() {
		System.out.println("in def ctor " + getClass());
	}

	//add REST end point to get all emps
	// http:host:port/employees , method=GET
	@GetMapping
	public List<Employee> getAllEmps() {
		System.out.println("in get all emps");
		return empService.getAllEmpDetails();
	}// Handler rets @RespBody List --> D.S (SC) --> SC performs marshalling(java
		// ---> JSON) --> sent to front end
		// add REST end point to add new emp details
		// http:host:port/employees , method=POST

	@PostMapping
	public Employee saveEmpDetails(@RequestBody Employee transientEmp) {
		System.out.println("in save emp " + transientEmp + " id " + transientEmp.getId());//
		return empService.addEmpDetails(transientEmp);

	}

	// http:host:port/employees/123 , method=DELATE
	@DeleteMapping("/{empId}")
	public ApiResponse deleteEmpDetails(@PathVariable Long empId) {
		System.out.println("in del emp " + empId);
		return new ApiResponse(empService.deleteEmpDetails(empId));
	}

	// http:host:port/employees/123 , method=GET
	@GetMapping("/{empId}")
	public Employee getEmpDetails(@PathVariable Long empId) {
		System.out.println("in get emp details " + empId);
		// invoke service layer method to get emp details
		return empService.fetchEmpDetails(empId);
	}

	// http:host:port/employees , method=PUT
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee detachedEmp) {
		System.out.println("in update emp " + detachedEmp.getId());// not null
		return empService.updateEmpDetails(detachedEmp);
	}



}
