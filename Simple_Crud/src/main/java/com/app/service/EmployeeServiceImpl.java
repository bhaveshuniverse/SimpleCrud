package com.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;

import com.app.pojos.Employee;
import com.app.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	// dep : dao layer i/f
	@Autowired
	private EmployeeRepository empRepo;
	// dep : model mapper
	@Autowired
	private ModelMapper mapper;

	@PostConstruct
	public void init() {
		System.out.println("in init " + mapper);
	}

	@Override
	public List<Employee> getAllEmpDetails() {
		return empRepo.findAll();
	}

	@Override
	public Employee addEmpDetails(Employee transientEmp) {
		// TODO Auto-generated method stub
		return empRepo.save(transientEmp);
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		// chekc if emp exists by id
		if (empRepo.existsById(empId)) {
			empRepo.deleteById(empId);
			return "Emp details deleted ....";
		}
		return "Deletion Failed : Invalid Emp Id !!!!!!!!!!!";
	}

	@Override
	public Employee fetchEmpDetails(Long empId) {
		// TODO Auto-generated method stub
		return empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!!"));
	}

	@Override
	public Employee updateEmpDetails(Employee detachedEmp) {
		// confirm if emp with id exists !
		if (empRepo.existsById(detachedEmp.getId())) {
			return empRepo.save(detachedEmp);
		}
		throw new ResourceNotFoundException("Invalid Emp Id : Updation Failed!!!!!!!!");
	}



}
