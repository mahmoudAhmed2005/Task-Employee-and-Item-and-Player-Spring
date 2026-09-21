package com.employee_springdemo.employeespring.controller;

import com.employee_springdemo.employeespring.model.Employee;
import com.employee_springdemo.employeespring.service.EmployeeService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// يرجّع نتائج الدوال مباشرة في استجابة HTTP، مثل JSON
@RestController
// المسار ده بيتضاف قبل المسار المكتوب على كل دالة
@RequestMapping("/employee")
public class EmployeeController {

    public EmployeeService employeeService;

    // Spring يمرّر EmployeeService للـController عند إنشائه
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // GET /employee/employee
    // يجلب كل الموظفين
    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    // GET /employee/employees/by_id?id=1,2
    // يقرأ قائمة IDs من عنوان الطلب ويجلب الموظفين المرتبطين بها
    @GetMapping("/employees/by_id")
    public List<Employee> getAllEmployeeById(@RequestParam List<Long> id) {
        return employeeService.getEmployeesById(id);
    }

    // POST /employee/employee
    // يقرأ بيانات موظف واحد من الـBody ويضيفه
    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee)
            throws SystemException {
        return employeeService.addEmployee(employee);
    }

    // POST /employee/employees
    // يقرأ قائمة موظفين من الـBody ويضيفهم
    @PostMapping("/employees")
    public List<Employee> addEmployees(@RequestBody List<Employee> employees)
            throws SystemException {
        return employeeService.addEmployees(employees);
    }

    // PUT /employee/employee
    // يقرأ بيانات موظف واحد من الـBody ويرسلها للـService لتعديلها
    @PutMapping("/employee")
    public Employee updateEmployee(@RequestBody Employee employee)
            throws SystemException {
        return employeeService.updateEmployee(employee);
    }

    // PUT /employee/employees
    // يقرأ قائمة موظفين من الـBody ويرسلها للـService لتعديلها
    @PutMapping("/employees")
    public List<Employee> updateEmployees(@RequestBody List<Employee> employees)
            throws SystemException {
        return employeeService.updateEmployees(employees);
    }

    // DELETE /employee/employees/{id}
    // يستدعي دالة حذف كل الموظفين
    @DeleteMapping("/employees/{id}")
    public void deleteAllEmployees() {
        employeeService.deleteAllEmployees();
    }

    // DELETE /employee/employee/{id}
    // يأخذ الـID من عنوان الطلب ويحذف الموظف المقابل له
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    // DELETE /employee/employees/by-ids
    // يقرأ قائمة IDs من الـBody ويحذف الموظفين المرتبطين بها
    @DeleteMapping("/employees/by-ids")
    public void deleteEmployeesByIds(@RequestBody List<Long> id) {
        employeeService.deleteEmployeesByIds(id);
    }

    // GET /employee/players/name/{name}
    // يأخذ الاسم من عنوان الطلب ويبحث عن موظف بهذا الاسم
    @GetMapping("/players/name/{name}")
    public Employee findByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    // GET /employee/players/search/{name}
    // يبحث عن الموظفين الذين تحتوي أسماؤهم على النص المرسل
    // مع تجاهل الفرق بين الحروف الكبيرة والصغيرة
    @GetMapping("/players/search/{name}")
    public List<Employee> findByNameContainingIgnoreCase(@PathVariable String name) {
        return employeeService.findByNameContainingIgnoreCase(name);
    }

    // GET /employee/players/{id}
    // يأخذ الـID من عنوان الطلب ويجلب الموظف المقابل له
    @GetMapping("/players/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
}