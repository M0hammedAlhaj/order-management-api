package com.spring.restapi.service;

import com.spring.restapi.dao.RoleDao;
import com.spring.restapi.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(readOnly = true)
    public Role findRoleByAuthority(String authority) throws RoleNotFoundException {
        Optional<Role> role = roleDao.findByAuthorize(authority);
        if (role.isPresent()) {
            return role.get();
        }
        throw new RoleNotFoundException(authority);
    }
}
