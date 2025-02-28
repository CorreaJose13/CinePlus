package com.correajose.cineplus.models.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private boolean enabled;
}
