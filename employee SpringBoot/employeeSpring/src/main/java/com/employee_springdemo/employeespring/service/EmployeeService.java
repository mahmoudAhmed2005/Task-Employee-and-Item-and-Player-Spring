package com.employee_springdemo.employeespring.service;

import com.employee_springdemo.employeespring.model.Employee;
import jakarta.transaction.SystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    // يرجّع كل الموظفين الموجودين في قاعدة البيانات
    List<Employee> getAllEmployee();

    // يستقبل قائمة IDs ويرجّع الموظفين الموجودين بهذه الأرقام
    List<Employee> getEmployeesById(List<Long> id);


    // يضيف موظفًا واحدًا؛ وقد يرمي SystemException إذا فشل التحقق
    Employee addEmployee(Employee employee) throws SystemException;

    // يضيف قائمة من الموظفين
    List<Employee> addEmployees(List<Employee> employees) throws SystemException;


    // يعدّل بيانات موظف واحد باستخدام الـID الموجود داخل Employee
    Employee updateEmployee(Employee employee) throws SystemException;

    // يعدّل بيانات مجموعة من الموظفين
    List<Employee> updateEmployees(List<Employee> employees) throws SystemException;


    // يحذف جميع الموظفين
    void deleteAllEmployees();

    // يحذف موظفًا واحدًا باستخدام الـID
    void deleteEmployee(Long id);

    // يحذف مجموعة موظفين باستخدام قائمة IDs
    void deleteEmployeesByIds(List<Long> id);


    // يبحث عن موظف باسمه الكامل
    Employee findByName(String name);

    // يبحث عن الموظفين الذين تحتوي أسماؤهم على النص المُرسل
    // IgnoreCase معناها تجاهل الفرق بين الحروف الكبيرة والصغيرة
    List<Employee> findByNameContainingIgnoreCase(String name);


    // يرجّع موظفًا واحدًا باستخدام الـID
    Employee getEmployeeById(Long id);
}