package com.crud.controller;

import java.util.List;

import com.crud.dao.EmployeeDAO;
import com.crud.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRESTController {

	@Autowired
	private EmployeeDAO employeeDAO;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	// URL:
	// http://localhost:8080/SomeContextPath/getemployees
	// http://localhost:8080/SomeContextPath/getemployees.xml
	// http://localhost:8080/SomeContextPath/getemployees.json
	@RequestMapping(value = "/getemployees", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Employee> getEmployees() {
		List<Employee> list = employeeDAO.getAllEmployees();
		
		return list;
	}

	// URL:
	// http://localhost:8080/SomeContextPath/getemployee/{empNo}
	// http://localhost:8080/SomeContextPath/getemployee/{empNo}.xml
	// http://localhost:8080/SomeContextPath/getemployee/{empNo}.json
	@RequestMapping(value = "/getemployee/{empNo}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee getEmployee(@PathVariable("empNo") String empNo) {
		//System.out.println("inside getEmployees");
		return employeeDAO.getEmployee(empNo);
	}

	// URL:
	// http://localhost:8080/SomeContextPath/postemployee
	// http://localhost:8080/SomeContextPath/postemployee.xml
	// http://localhost:8080/SomeContextPath/postemployee.json

	@RequestMapping(value = "/postemployee", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee addEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());

		return employeeDAO.addEmployee(emp);
	}

	// URL:
	// http://localhost:8080/SomeContextPath/putemployee
	// http://localhost:8080/SomeContextPath/putemployee.xml
	// http://localhost:8080/SomeContextPath/putemployee.json
	@RequestMapping(value = "/putemployee", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE, //
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Employee updateEmployee(@RequestBody Employee emp) {

		System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());

		return employeeDAO.updateEmployee(emp);
	}

	// URL:
	// http://localhost:8080/SomeContextPath/delemployee/{empNo}
	@RequestMapping(value = "/delemployee/{empNo}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void deleteEmployee(@PathVariable("empNo") String empNo) {

		System.out.println("(Service Side) Deleting employee: " + empNo);

		employeeDAO.deleteEmployee(empNo);
	}

}