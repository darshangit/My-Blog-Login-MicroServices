package com.angular.services.entity;

import javax.persistence.*;

/**
 * Created by Darsh on 5/13/2017.
 */
@Entity
@Table(name="USER_DETAILS")
public class UserEntity {

    private Integer UUID;
    private String name;
    private String email;
    private String password;

    public UserEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_UUID")
    public Integer getUUID() {
        return UUID;
    }

    @Column(name="USER_NAME")
    public String getName() {
        return name;
    }

    @Column(name="USER_EMAIL")
    public String getEmail() {
        return email;
    }

    @Column(name="USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setUUID(Integer UUID) {
        this.UUID = UUID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
