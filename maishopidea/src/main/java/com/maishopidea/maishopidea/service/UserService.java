package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
     @Nullable
     User findByEmail(String userEmail) throws Exception;
//     User findByUserId(String userId) throws Exception;
     User getUser(String Email);
     int saveUser(User prod);
     User findById(int userId);
}
