package com.first_springboot.demo.model;

import jakarta.persistence.*;

@Entity
public class Player {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(unique = true)
    private String name;

    @Column(name="player_number")
    private Integer number;

    private Double salary;




    private String Details;


    public Player() {
    }

    public Player(Long id, String name, Integer number, Double salary) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }


    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", salary=" + salary +
                '}';
    }
}
