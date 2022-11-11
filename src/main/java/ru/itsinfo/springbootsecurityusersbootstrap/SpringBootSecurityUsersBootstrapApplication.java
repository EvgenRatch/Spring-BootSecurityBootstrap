package ru.itsinfo.springbootsecurityusersbootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itsinfo.springbootsecurityusersbootstrap.model.Role;
import ru.itsinfo.springbootsecurityusersbootstrap.model.User;
import ru.itsinfo.springbootsecurityusersbootstrap.dao.RoleDao;
import ru.itsinfo.springbootsecurityusersbootstrap.dao.UserDao;

import java.util.HashSet;

@SpringBootApplication
public class SpringBootSecurityUsersBootstrapApplication implements CommandLineRunner {

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SpringBootSecurityUsersBootstrapApplication(RoleDao roleDao,
                                                       UserDao userDao,
                                                       PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityUsersBootstrapApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleDao.save(admin);
        roleDao.save(user);
        roleDao.save(new Role("ROLE_GUEST"));

        userDao.save(new User("Александр", "Петров", 32, "petrov@mail.com",
                passwordEncoder.encode("petrovActor"),
                new HashSet<>() {{
                    add(admin);
                    add(user);
                }}));
        userDao.save(new User("Константин", "Хабенский", 50, "khabenskiy@mail.ru",
                passwordEncoder.encode("user"),
                new HashSet<>() {{
                    add(user);
                }}));
    }
}
