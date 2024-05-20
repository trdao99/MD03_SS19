package com.ra.md03_ss18.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name is empty!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Address is empty!")
    @Column(name = "address")
    private String address;
    @Past(message = "date is not valid")
    @NotNull(message = "date is null")
    @Column(name = "dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotNull(message = "Phonmydbe is empty!")
    @Column(name = "phone")
    private Long phone;
}

