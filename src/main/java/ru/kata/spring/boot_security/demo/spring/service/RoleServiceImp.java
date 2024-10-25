package ru.kata.spring.boot_security.demo.spring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.spring.model.Role;
import ru.kata.spring.boot_security.demo.spring.repository.RoleRepository;

import java.util.List;

@Service
@Qualifier("serviceRole")
public class RoleServiceImp implements RoleService {
    private RoleRepository repository;

    public RoleServiceImp(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<Role> getRoleList() {
        return (List<Role>) repository.findAll();
    }
}
