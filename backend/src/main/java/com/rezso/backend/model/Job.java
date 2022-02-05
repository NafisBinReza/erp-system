package com.rezso.backend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_store")
public class Job {


    @Id
    private int id;
    private String jobTitle;
    private String department;
    private int currentEmployees;
    private int expectedEmployees;
    private int forecastedEmployees;
    private int hiredEmployees;
    private String status;
}
