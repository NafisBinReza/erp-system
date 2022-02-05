package com.rezso.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_store")
public class Employee {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String username;
    private String password;
    private String roles;
    private String workingAddress;
    private String workMobile;
    private String workLocation;
    private String workEmail;
    private String workPhone;
    private String department;
    private String jobTitle;
    private String manager;
    private String coach;
    private String nationality;
    private String identificationNo;
    private String passport;
    private String bankAccountNo;
    private String gender;
    private String maritalStatus;
    private String homeAddress;
    private String dateOfBirth;
}
