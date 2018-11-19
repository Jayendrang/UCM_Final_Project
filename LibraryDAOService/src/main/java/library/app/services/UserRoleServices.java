package library.app.services;

import org.springframework.data.jpa.repository.JpaRepository;
import library.app.dao.model.user_roles;

public interface UserRoleServices extends JpaRepository<user_roles,Integer> {

}
