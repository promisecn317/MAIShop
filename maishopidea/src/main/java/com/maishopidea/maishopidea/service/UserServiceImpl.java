package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.repo.UserRepo;
import com.maishopidea.maishopidea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserRepo userRepo;

    @Override
    public User findByEmail(String Email) {
        return userRepo.findByEmail(Email);
    }
    @Override
    public User findByPassword(String Password) {
        return userRepo.findByPassword(Password);
    }
//
//    @Override
//    public  User findByUserEmail(String userEmail) throws Exception{
//        User user=userRepo.findByUserEmail(userEmail);
//        if (user==null){// user does not exist
//            throw new Exception("user does not exist");
//        }else{
//            if(!user.getUserPassword().equals(userPassword.trim())){//Password error
//            throw new Exception("Password error");
//            }
//        }
//        return user;
//    }

}
