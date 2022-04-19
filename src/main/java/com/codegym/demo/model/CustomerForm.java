package com.codegym.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerForm {
    private int id;
    private String name;
    private String email;
    private String address;
    private MultipartFile avatar;

    public CustomerForm() {
    }

    public CustomerForm(int id, String name, String email, String address, MultipartFile avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}
