package ru.kata.spring.boot_security.demo.spring.model;

public enum RoleEnum {
    USER, ADMIN;

    private String stringRole;

    public String getStringRole() {
        return this.toString();
    }

}
