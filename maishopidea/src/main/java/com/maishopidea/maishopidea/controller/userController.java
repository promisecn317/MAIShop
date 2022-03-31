package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.user;
import com.maishopidea.maishopidea.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class userController {

    @Autowired
    private userRepo userRepo;

//    @PostMapping(value = "getUserPassword")
//    public List<user> getUserEmail(){
//        return userRepo.findAllByuserEmail();
//    }
//    @PostMapping(value = "getUserPassword")
//    public List<user> getUserPassword(){
//        return userRepo.findAllByuserPasswoed();
//    }
//

    @GetMapping(value = "checkEmailPassword/{userinputEmail}")
    public user getInputeMail(@PathVariable("userinputEmail") int userId){
        Optional<user> user=userRepo.findById(userId);
        return user.isPresent()?user.get():null;
    }
    /**
     * 3-24 疑惑 : optional  user.get()   findById() isPresent() 区别  user.get()得到一个 user类型的数据
     */
//获取所有user， 判断传来的email psw 是否在表中 findAllby(), email&psw 同时在表中 return 成功， else 失败

/** 新写一个方法   post 将input存入  然后再对比 if else return  */
//    @PostMapping(value = "userLogin")
//    public String userLogin(@RequestBody user userLogin){
//        userRepo.save(userLogin);
//        return "0";
//        //userRepo.save(userLogin).getUserEmail();
//    }

/** 与上一个区别是  public user  */
//    @PostMapping(value = "userLogin")
//    public user userLogin(@RequestBody user userLogin){
//        userRepo.save(userLogin);
//        return userRepo.save(userLogin);
//        //userRepo.save(userLogin).getUserEmail();
//    }



//    @GetMapping(value = "userEmail")
//    public String getUserEmail(@RequestParam(name="userEmail") user userEmail){
//
//        return userEmail;
//    }

//    @GetMapping(value = "userPassword/{userPassword}")
//    public String getUserPassword(@PathVariable(name="userPassword") String UserPassword){
//
//        return UserPassword;
//    }


}
