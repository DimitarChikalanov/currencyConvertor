package com.currency.convertor.service.serviceImpl;

import com.currency.convertor.domain.entity.Erole;
import com.currency.convertor.domain.entity.Role;
import com.currency.convertor.domain.entity.User;
import com.currency.convertor.repository.RoleRepository;
import com.currency.convertor.service.RolesService;
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
       List<Erole> roles = new ArrayList<>();
        if (this.roleRepository.count() == 0) {
            roles.add(Erole.ROLE_ADMIN);
            roles.add(Erole.ROLE_USER);
            roles.forEach(e -> {
                Role roleAdmin = new Role();
                roleAdmin.setErole(e);
                this.roleRepository.saveAndFlush(roleAdmin);
            });

        }
    }
}
