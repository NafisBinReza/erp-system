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
@Table(name = "leave_store")
public class Leave {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private int employeeId;
    private String employee;
    private String requestType;
    private String description;
    private int numberOfDays;
    private Date startDate;
    private Date endDate;
    private String leaveType;
    private String status;
    private String department;
    private String comment;
}
