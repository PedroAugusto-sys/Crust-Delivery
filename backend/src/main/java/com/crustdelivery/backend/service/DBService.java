package com.crustdelivery.backend.service;

import com.crustdelivery.backend.repository.RouteRepository;
import com.crustdelivery.backend.repository.UserRepository;
import com.crustdelivery.backend.model.User;
import com.crustdelivery.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class DBService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Transactional
    public void instanciaDB() {
        routeRepository.deleteAll();
        User user = new User(null, "user", Utils.hashPassword("user"), "ROLE_ADMIN");
        //userRepository.save(user);

    }
}
