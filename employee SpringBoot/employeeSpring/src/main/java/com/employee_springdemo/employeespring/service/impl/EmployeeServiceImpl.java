package com.employee_springdemo.employeespring.service.impl;

import com.employee_springdemo.employeespring.model.Employee;
import com.employee_springdemo.employeespring.repo.EmployeeRepo;
import com.employee_springdemo.employeespring.service.EmployeeService;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    private EmployeeRepo employeeRepo;
@Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }



    @Override
    public List<Employee> getEmployeesById(List<Long> id) {
        return employeeRepo.findAllById(id);
    }



    @Override
    public Employee addEmployee(Employee employee) throws SystemException {

    if(Objects.nonNull(employee.getId())){
        throw new SystemException("id must be  null");
    }

    Optional<Employee>optionalEmployee= employeeRepo.findByName(employee.getName());
    if(optionalEmployee.isPresent()){
        throw new SystemException("name is exist"+employee.getName());
    }
        return employeeRepo.save(employee);
    }



    @Override
    @Transactional
    public List<Employee> addEmployees(List<Employee> employees)throws SystemException {
    List<Employee> saveEmployees = new ArrayList<>();
    for (Employee employee : employees){
        saveEmployees.add(addEmployee(employee));
    }
        return saveEmployees;
    }

    @Override
    public Employee updateEmployee(Employee employee) throws SystemException {
    if (Objects.isNull(employee.getId())){
        throw new SystemException("id must be not null");
    }
    Optional<Employee>optionalEmployee=employeeRepo.findById(employee.getId());

    if (optionalEmployee.isEmpty()){
        throw new SystemException("id must be not null");
    }


    if (optionalEmployee.get().getName().equals(employee.getName())){
        return employeeRepo.save(employee);
    }

    optionalEmployee = employeeRepo.findByName(employee.getName());
    if (optionalEmployee.isPresent()){
        throw new SystemException("name alrde exist");
    }

        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> updateEmployees(List<Employee> employees)throws SystemException  {
        List<Employee>updateEmployees = new ArrayList<>();

        for (Employee employee : employees){
            updateEmployees.add(updateEmployee(employee));
        }
        return updateEmployees;
    }

    @Override
    public void deleteAllEmployees() {

    employeeRepo.deleteAll();

    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public void deleteEmployeesByIds(List<Long> id) {
       employeeRepo.findAllById(id);
    }

    @Override
    public Employee findByName(String name)  {
        return employeeRepo.findByName(name).get();
    }

    @Override
    public List<Employee>findByNameContainingIgnoreCase(String name) {
        return employeeRepo.findByNameContainingIgnoreCase(name).get();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).get();
    }
}
