package ru.kata.spring.boot_security.demo.spring.model;




import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;
    @NotBlank(message = "Логин не должен быть пустым")
    private String login;
    @NotBlank(message = "Пароль не должно быть пустым")
    @Size(min = 5, message = "Минимальная длина пароль 5 символов")
    private String password;
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 2, message = "Имя должно включать минимум два символа")
    private String name;
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(min = 2, message = "Фамилия должна включать минимум два символа")
    private String surname;
    @Min(value = 0, message = "возраст не может быть меньше 0")
    private Integer age;

    @Column(name = "roles")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "users_id"),
        inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roleSet = new HashSet<>();

    public User() {
    }

    public User(String login, String password, String name, String surname, Integer age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
           this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    @Override
    public String toString() {
        return "Пользователь{" +
                "id=" + id +
                ", имя='" + name + '\'' +
                ", фамилия='" + surname + '\'' +
                ", возраст=" + age +
                '}';
    }
}
