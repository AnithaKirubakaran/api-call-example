package com.example.practice.service;

import com.example.practice.entity.Employee;
import com.example.practice.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.practice.exception.EmployeeNotFound;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl {
    @Autowired
    EmployeeRepository employeeRepository;

    public List getAllEmployees(){
        List employees = new ArrayList();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public void updateAndSave(Employee employee){
        employeeRepository.save(employee);
    }

    public Object getEmployeesById(int id) throws EmployeeNotFound {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            throw new EmployeeNotFound("No Such Employee Id" +id+ "Found");
        }
        return  employeeRepository.findById(id).get();
    }

    public void deleteEmployeesById(int id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> finddetails() {
        List<Employee> emp = new ArrayList<>();
        return employeeRepository.findAll();
    }
}
