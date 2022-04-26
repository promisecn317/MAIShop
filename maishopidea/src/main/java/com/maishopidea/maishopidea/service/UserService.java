package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
     @Nullable
     User findByEmail(String userEmail) throws Exception;
     User findByPassword(String userPassword) throws Exception;
     public User getUser(String Email);
     public int saveUser(User prod);
     //public User userLogin(String userEmail, String password);

}
