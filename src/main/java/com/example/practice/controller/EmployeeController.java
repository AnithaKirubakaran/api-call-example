package com.example.practice.controller;

import com.example.practice.entity.Employee;
import com.example.practice.exception.EmployeeNotFound;
import com.example.practice.repo.EmployeeRepository;
import com.example.practice.service.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EmployeeController {

    @Autowired
    EmployeeImpl employeeImpl;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/web")
    public String welcome() {
        return "hi";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public List<Employee> listOfEmployee() {
        return employeeImpl.getAllEmployees();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public int createNewEmployee(@RequestBody Employee employee) {
        employeeImpl.updateAndSave(employee);
        return employee.getId();
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public Object findEmployeeById(@PathVariable("id") int id) {
        try {
            return (Employee) employeeImpl.getEmployeesById(id);
        } catch (EmployeeNotFound e) {
            return "No Employee Found with id " + id;
        }
    }

    //PUT Request need to send the full payload as the request
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) throws EmployeeNotFound {
        Employee getEmployee = (Employee) employeeImpl.getEmployeesById(id);
        if (getEmployee != null) {
            employeeImpl.updateAndSave(employee);
        }
        return "Employee Id " + id + " Updated Successfully";
    }

    /*//PATCH Request for sending the data which we want to update
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PATCH, produces = "application/json;charset=utf-8")
    public void patchUpdateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) throws EmployeeNotFound {
        Employee getemployee = (Employee) employeeImpl.getEmployeesById(id);
        if (getemployee != null) {
            employeeImpl.updateAndSave(employee);
        }
    }*/

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public String deleteEmployee(@PathVariable("id") int id) {
        employeeImpl.deleteEmployeesById(id);
        return "Employee Id " + id + " Deleted Successfully";
    }
}
