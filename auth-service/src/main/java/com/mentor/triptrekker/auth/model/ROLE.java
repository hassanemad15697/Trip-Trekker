package com.mentor.triptrekker.auth.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mentor.triptrekker.auth.model.Permission.*;

@Getter
@RequiredArgsConstructor
public enum ROLE {



    USER(Collections.emptySet()),
    ADMIN(new Object() {
        Set<Permission> evaluate() {
            Set<Permission> adminPermissions = new java.util.HashSet<>();
            adminPermissions.add(ADMIN_READ);
            adminPermissions.add(ADMIN_UPDATE);
            adminPermissions.add(ADMIN_DELETE);
            adminPermissions.add(ADMIN_CREATE);
            adminPermissions.add(MANAGER_READ);
            adminPermissions.add(MANAGER_UPDATE);
            adminPermissions.add(MANAGER_DELETE);
            adminPermissions.add(MANAGER_CREATE);
            return adminPermissions;
        }
    }.evaluate()),
    MANAGER(new Object() {
        Set<Permission> evaluate() {
            Set<Permission> managerPermissions = new java.util.HashSet<>();
            managerPermissions.add(MANAGER_READ);
            managerPermissions.add(MANAGER_UPDATE);
            managerPermissions.add(MANAGER_DELETE);
            managerPermissions.add(MANAGER_CREATE);
            return managerPermissions;
        }
    }.evaluate());

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
