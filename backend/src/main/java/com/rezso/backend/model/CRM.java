package com.rezso.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "crm_store")
public class CRM {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String website;
    private String phone;
    private String mobile;
    private String fax;
    private String email;
    private String language;
}
