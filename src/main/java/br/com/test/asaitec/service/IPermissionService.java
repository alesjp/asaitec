package br.com.test.asaitec.service;

import br.com.test.asaitec.model.Permission;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {

    Optional<Permission> saveOrUpdate(Permission permissionDto);

    Optional<Permission> findById(Long id);

    List<Permission> findAll();

    void deleteById(Long id);

    Optional<Permission> findByPermissionName(String permissionName);
}