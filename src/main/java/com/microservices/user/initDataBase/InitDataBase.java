package com.microservices.user.initDataBase;

import com.microservices.user.enums.RoleEnum;
import com.microservices.user.model.Roles;
import com.microservices.user.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class InitDataBase {

    @Autowired
    private RoleRepository roleDao;

    @Bean
    CommandLineRunner initDatabaseBank() {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                initDataRoles();
            }
        };
    }

    private void initDataRoles() {
        List<Roles> roleLst = new ArrayList<>();
        Roles role = Roles.builder().roleName(RoleEnum.USER).isDeleted(false).build();
        roleLst.add(role);
        role = Roles.builder().roleName(RoleEnum.ADMIN).isDeleted(false).build();
        roleLst.add(role);
        role = Roles.builder().roleName(RoleEnum.OPERATION).isDeleted(false).build();
        roleLst.add(role);

        // Save Role
        roleDao.saveAll(roleLst);

        log.info("Create data Role {}", roleLst);
    }
}
