package ru.kata.spring.boot_security.demo.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import ru.kata.spring.boot_security.demo.spring.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRoleList();
}
