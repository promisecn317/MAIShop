package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.repo.UserRepo;
import com.maishopidea.maishopidea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServcie {
    @Autowired
    private UserRepo userRepo;


    @Override
    public  User findByUserEmail(String userEmail, String userPassword) throws Exception{
        User user=userRepo.findByUserEmail(userEmail);
        if (user==null){// user does not exist
            throw new Exception("user does not exist");
        }else{
            if(!user.getUserPassword().equals(userPassword.trim())){//Password error
            throw new Exception("Password error");
            }
        }
        return user;

    }

    @Override
    public User userLogin(String userEmail, String password) {
        return userRepo.userLogin(userEmail, password);
    }
}
