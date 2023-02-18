package br.com.test.asaitec.repository;

import br.com.test.asaitec.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByPermissionName(String permissionName);

}
