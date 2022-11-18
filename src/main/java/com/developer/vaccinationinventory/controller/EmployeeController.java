package com.developer.vaccinationinventory.controller;

import com.developer.vaccinationinventory.entity.EmployeeEntity;
import com.developer.vaccinationinventory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static com.developer.vaccinationinventory.entity.EmployeeEntity.registerValidation;
import static com.developer.vaccinationinventory.entity.EmployeeEntity.updateValidation;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public EmployeeEntity saveEmployee(@RequestBody EmployeeEntity employeeEntity, @RequestHeader(value= "user_role") String user_role) {
        String user = employeeEntity.getName() + employeeEntity.getLegal_id().toString();
        String newPassword = employeeEntity.passwordGenerator(11);
        employeeEntity.setUsername(user);
        employeeEntity.setPassword(newPassword);
        System.out.println(user_role);

        return registerValidation(employeeEntity,user_role,employeeService);
    }

    @PatchMapping(value = "/updateInfo/{id}")
    public EmployeeEntity updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeEntity employeeEntity, @RequestHeader(value= "user_role") String user_role){
        employeeEntity.setId(id);
        return updateValidation(employeeEntity,user_role,employeeService);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<EmployeeEntity> findEmployee(@RequestHeader(value= "user_role") String user_role){
        if(user_role.equals("ADMIN")) {
            return employeeService.findAllEmployee();
        } else {
            throw new RuntimeException("Only ADMIN role can show all employees");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("id") Integer id, @RequestHeader(value= "user_role") String user_role){
        if(user_role.equals("ADMIN")) {
            employeeService.deleteEmployee(id);
        } else {
            throw new RuntimeException("Only ADMIN role can delete employee information");
        }
    }

    @RequestMapping(value = "/myInfo/{id}", method = RequestMethod.GET)
    public Optional<EmployeeEntity> findEmployeeById(@PathVariable("id") Integer id, @RequestHeader(value= "user_role") String user_role){
        if(user_role.equals("USER")) {
            return employeeService.findById(id);
        } else {
            throw new RuntimeException("Only USER role can show their information");
        }
    }




}
