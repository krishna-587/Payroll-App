package com.example.Payroll.App.service;

import com.example.Payroll.App.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    private int idCounter = 1; // Counter for generating employee IDs

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    // Get employee by ID
    public Employee getEmployeeById(int id) {
        return employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        employee.setId(idCounter++); // Assign an ID
        employeeList.add(employee);
        return employee;
    }

    // Update an employee
    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOpt = employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst();

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            return existingEmployee;
        }
        return null;
    }

    // Delete an employee
    public boolean deleteEmployee(int id) {
        return employeeList.removeIf(emp -> emp.getId() == id);
    }
}
