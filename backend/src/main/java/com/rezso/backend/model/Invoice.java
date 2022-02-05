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
@Table(name = "invoice_store")
public class Invoice {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String customer;
    private Date date;
    private String number;
    private String salesperson;
    private Date dueDate;
    private String document;
    private long price;
    private long quantity;
    private long total;
    private long amountDue;
    private String status;
}
