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
@Table(name = "recruitment_store")
public class Recruitment {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private Date creationDate;
    private String subject;
    private String email;
    private String phone;
    private String mobile;
    private String jobTitle;
    private String stage;
    private String medium;
    private String source;
    private String appreciation;
    private String responsible;
    private String department;
    private String degree;
    private String expectedSalary;
    private String proposedSalary;
    private String availability;
}

