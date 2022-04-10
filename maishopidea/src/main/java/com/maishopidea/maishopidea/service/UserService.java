package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {


     User findByEmail(String userEmail) throws Exception;
     User findByPassword(String userPassword) throws Exception;

    //public User userLogin(String userEmail, String password);

}
