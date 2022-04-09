package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserServcie {


    public User findByUserEmail(String userEmail,String userPassword) throws Exception;

    public User userLogin(String userEmail, String password);

}
