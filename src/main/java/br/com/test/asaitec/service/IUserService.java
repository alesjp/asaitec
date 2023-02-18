package br.com.test.asaitec.service;

import br.com.test.asaitec.model.Permission;
import br.com.test.asaitec.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> saveOrUpdate(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> addPermission(Long userId, Permission permission);
}