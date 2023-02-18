package br.com.test.asaitec.service;

import br.com.test.asaitec.exception.ResourceNotFoundException;
import br.com.test.asaitec.exception.ValidationException;
import br.com.test.asaitec.model.Permission;
import br.com.test.asaitec.model.User;
import br.com.test.asaitec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final List<String> PERMISSION_A_AND_B = Arrays.asList("PERMISSION A", "PERMISSION B");

    private static final List<String> PERMISSION_A_AND_C = Arrays.asList("PERMISSION A", "PERMISSION C");

    @Autowired
    private UserRepository repository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Optional<User> saveOrUpdate(User user) {
        return Optional.of(repository.save(user));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id)));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<User> addPermission(Long userId, Permission permission) {
        User user = findById(userId).map(tempUser -> {

            Permission tempPermission = permissionService.findById(permission.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Permission with id = " + permission.getId()));

            if (isValidInsert(tempUser, tempPermission)) {
                tempUser.getPermissions().add(tempPermission);
                saveOrUpdate(tempUser);
            }

            checkIfShouldAddPermisionD(tempUser);

            return tempUser;

        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

        return Optional.of(user);
    }

    public boolean isValidInsert(User user, Permission permission) {
        // avoid duplicate
        if (user.getPermissions().contains(permission)) {
            throw new ValidationException(String.format("The user with id %s already have permission with id %s", user.getId(), permission.getId()));
        }

        // In case PERMISSION A and PERMISSION B is added PERMISSION C should not be added
        List<String> userPermissions = user.getPermissions().stream().map(Permission::getPermissionName).toList();
        if (userPermissions.containsAll(PERMISSION_A_AND_B) && permission.getPermissionName().equals("PERMISSION C")) {
            throw new ValidationException("In case PERMISSION A and PERMISSION B is added PERMISSION C should not be added");
        }

        return true;
    }

    public void checkIfShouldAddPermisionD(User user) {
        // In case PERMISSION A and PERMISSION C is added PERMISION D should be automatically added
        List<String> userPermissions = user.getPermissions().stream().map(Permission::getPermissionName).toList();
        if (userPermissions.containsAll(PERMISSION_A_AND_C)) {
            Optional<Permission> tempPermission = permissionService.findByPermissionName("PERMISSION D");

            if (tempPermission.isPresent()) {
                user.getPermissions().add(tempPermission.get());
                saveOrUpdate(user);
            }
        }
    }
}