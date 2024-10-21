package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.kata.spring.boot_security.demo.spring.model.Role;
import ru.kata.spring.boot_security.demo.spring.model.RoleEnum;
import ru.kata.spring.boot_security.demo.spring.model.User;
import ru.kata.spring.boot_security.demo.spring.model.UserService;
import ru.kata.spring.boot_security.demo.spring.service.UserServiceImp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		Role user = new Role(RoleEnum.USER.getStringRole());
		Role admin = new Role(RoleEnum.ADMIN.getStringRole());

		User userRoleUser = new User("user", "users", "Ваня", "Пупкин", 47);
		User adminRoleAdmin = new User("admin", "admin", "Мамкин", "Айтишник", 11);

		Set<Role> roleUserSet = new HashSet<>();
		roleUserSet.add(user);
		Set<Role> roleAdminSet = new HashSet<>();
		roleAdminSet.add(admin);

		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out :: println);


		UserService service = context.getBean("userServiceImp", UserServiceImp.class);
		service.addUser(userRoleUser);
		service.addUser(adminRoleAdmin);
	}

}
