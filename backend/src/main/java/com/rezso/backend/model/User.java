package com.rezso.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_store")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String username;
    private String password;
    private String roles;
    private String address_street;
    private String city;
    private String state;
    private String zip;
    private String website;
//    private String[] tags;
    private String phone;
    private String mobile;
    private String fax;
    private String email;
    private String language;
}
