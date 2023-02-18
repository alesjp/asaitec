package br.com.test.asaitec.repository;

import br.com.test.asaitec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
