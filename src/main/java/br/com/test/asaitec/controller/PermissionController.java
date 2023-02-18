package br.com.test.asaitec.controller;

import br.com.test.asaitec.model.Permission;
import br.com.test.asaitec.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private IPermissionService service;

    @GetMapping("/permissions")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        try {
            List<Permission> permission = service.findAll();

            if (permission.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(permission, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable("id") long id) {
        Optional<Permission> permission = service.findById(id);

        if (permission.isPresent()) {
            return new ResponseEntity<>(permission.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/permissions")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        try {
            permission.setId(null);
            return new ResponseEntity<>(service.saveOrUpdate(permission).get(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/permissions/{id}")
    public ResponseEntity<Permission> updateUser(@PathVariable("id") long id, @RequestBody Permission permission) {
        Optional<Permission> permissionToUpdate = service.findById(id);

        if (permissionToUpdate.isPresent()) {
            Permission tempPermission = permissionToUpdate.get();
            tempPermission.setPermissionName(permission.getPermissionName());
            return new ResponseEntity<>(service.saveOrUpdate(tempPermission).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/permissions/{id}")
    public ResponseEntity<HttpStatus> deletePermission(@PathVariable("id") long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
