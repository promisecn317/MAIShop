package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.repo.UserRepo;
import com.maishopidea.maishopidea.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired UserRepo userRepo;

    @Override
    public User findByEmail(String Email) {
        return userRepo.findByEmail(Email);
    }

//    @Override
//    public User findByUserId(String userId) {
//        return userRepo.findByUserId(userId);
//    }

    @Override
    public User getUser(String Email) {
        Optional<User> user = Optional.ofNullable(userRepo.findByEmail(Email));
        return user.orElse(null);
    }

    @Override
    public int saveUser(User user) {
        return userRepo.save(user).getUserId();//命名很重要
    }

    @Override
    public User findById(int userId) {
        Optional<User> user = userRepo.findById(userId);
        return user.orElse(null);
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
