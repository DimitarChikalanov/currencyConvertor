package com.currency.convertor.service.role;

import com.currency.convertor.domain.entity.ERole;
import com.currency.convertor.domain.entity.Role;
import com.currency.convertor.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    @PostConstruct
    public void saveToDb() {
       List<ERole> roles = new ArrayList<>();
        if (this.roleRepository.count() == 0) {
            roles.add(ERole.ROLE_ADMIN);
            roles.add(ERole.ROLE_USER);
            roles.forEach(e -> {
                Role role = new Role();
                role.setName(e);
                this.roleRepository.saveAndFlush(role);
            });

        }
    }
}
