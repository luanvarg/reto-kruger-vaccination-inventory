package com.developer.vaccinationinventory.entity;

import com.developer.vaccinationinventory.service.EmployeeService;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.security.SecureRandom;
import java.util.*;

@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Integer id;

    @NotNull
    @Column(name= "name")
    @Pattern(regexp = "^[a-zA-Z\u00f1]+(([',. ][a-zA-Z \u00f1])?[a-zA-Z\u00f1]*)*$")
    private String name;

    @NotNull
    @Column(name= "last_name")
    @Pattern(regexp = "^[a-zA-Z\u00f1]+(([',. ][a-zA-Z \u00f1])?[a-zA-Z\u00f1]*)*$")
    private String last_name;


    @Column(name = "email", unique = true)
    @NotEmpty(message="email address cannot be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Column(name = "legal_id", unique = true)
    @Pattern(regexp = "^[0-9]{10}$")
    private String legal_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    @Pattern(regexp = "^[0-9]{10}$")
    private String phone;

    @Column(name = "vaccination_status")
    private Boolean vaccination_status;

    @Column(name = "vaccine_type")
    private String vaccine_type;

    @Column(name = "vaccination_date")
    private Date vaccination_date;

    @Column(name = "dose_number")
    private Integer dose_number;

    @Column(name = "user_role")
    private String user_role = "USER";

    public EmployeeEntity() {
    }

    public EmployeeEntity(Integer id, String name, String last_name, String email, String legal_id, String username, String password, Date birthdate, String address, String phone, Boolean vaccination_status, String vaccine_type, Date vaccination_date, Integer dose_number, String user_role) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.legal_id = legal_id;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.vaccination_status = vaccination_status;
        this.vaccine_type = vaccine_type;
        this.vaccination_date = vaccination_date;
        this.dose_number = dose_number;
        this.user_role = user_role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegal_id() {
        return legal_id;
    }

    public void setLegal_id(String legal_id) {
        this.legal_id = legal_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getVaccination_status() {
        return vaccination_status;
    }

    public void setVaccination_status(Boolean vaccination_status) {
        this.vaccination_status = vaccination_status;
    }

    public String getVaccine_type() {
        return vaccine_type;
    }

    public void setVaccine_type(String vaccine_type) {
        this.vaccine_type = vaccine_type;
    }

    public Date getVaccination_date() {
        return vaccination_date;
    }

    public void setVaccination_date(Date vaccination_date) {
        this.vaccination_date = vaccination_date;
    }

    public Integer getDose_number() {
        return dose_number;
    }

    public void setDose_number(Integer dose_number) {
        this.dose_number = dose_number;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String passwordGenerator(Integer length){
        String inputString = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789qwertyuiopasdfghjklzxcvbnm!@#$%^&*()_+=-`~[]{}<>?/*-+";
        Random randomCharacter = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i =0;i<length;i++){
            builder.append(inputString.charAt(randomCharacter.nextInt(inputString.length())));
        }
        return builder.toString();
    }

    public static EmployeeEntity updateValidation(EmployeeEntity employeeEntity, String user_role, EmployeeService employeeService){
        if(user_role.equals("ADMIN") || user_role.equals("USER")) {
            if(employeeEntity.getVaccination_status() == true) {
                ArrayList<String> vaccine_catalogo = new ArrayList<>();
                vaccine_catalogo.add("Sputnik");
                vaccine_catalogo.add("AstraZeneca");
                vaccine_catalogo.add("Pfizer");
                vaccine_catalogo.add("Jhonson&Jhonson");

                if(vaccine_catalogo.contains(employeeEntity.getVaccine_type())){
                    return employeeService.updateEmployee(employeeEntity);
                }else{
                    throw new RuntimeException("This vaccine type does not exist");
                }
            }else {
                employeeEntity.setVaccine_type(null);
                employeeEntity.setVaccination_date(null);
                employeeEntity.setDose_number(0);
                return employeeService.updateEmployee(employeeEntity);
            }
        } else {
            throw new RuntimeException("This role does not exist");
        }
    }

    public static EmployeeEntity registerValidation(EmployeeEntity employeeEntity, String user_role, EmployeeService employeeService){
        if(user_role.equals("ADMIN")) {
                return employeeService.saveEmployee(employeeEntity);
        } else {
            throw new RuntimeException("Only ADMIN role can register employees");
        }
    }

}

