package ru.itsinfo.springbootsecurityusersbootstrap.dao;

import org.springframework.data.repository.CrudRepository;
import ru.itsinfo.springbootsecurityusersbootstrap.model.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
}
