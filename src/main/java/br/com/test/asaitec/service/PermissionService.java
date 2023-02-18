package br.com.test.asaitec.service;

import br.com.test.asaitec.exception.ResourceNotFoundException;
import br.com.test.asaitec.model.Permission;
import br.com.test.asaitec.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository repository;

    @Override
    public Optional<Permission> saveOrUpdate(Permission permission) {
        return Optional.of(repository.save(permission));
    }

    @Override
    public Optional<Permission> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Permission with id = " + id)));
    }

    @Override
    public List<Permission> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Permission> findByPermissionName(String permissionName) {
        return repository.findByPermissionName(permissionName);
    }
}