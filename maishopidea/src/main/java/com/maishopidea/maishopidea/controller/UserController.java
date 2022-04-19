package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Cart;
import com.maishopidea.maishopidea.entity.CartItem;
import com.maishopidea.maishopidea.entity.User;
import com.maishopidea.maishopidea.service.UserService;
import com.maishopidea.maishopidea.service.VerifyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private VerifyStatusService verifyService;
    @Autowired
    public void setVerifyService(VerifyStatusService verifyService){
        this.verifyService = verifyService;
    }

    @PostMapping(value="login")
    @ResponseBody

    // @RequestBody User acceptuser
    public Map<String, String> userLogin(@RequestParam(name = "userEmail") String inputUserEmail,
                                         @RequestParam("userPassword") String inputpassword
    ) throws Exception {
        //User user = userService.userLogin(inputUserEmail, inputpassword);

        User user =userService.findByEmail(inputUserEmail);
        String userpsw =user.getPassword();
        Map<String, String> result = new HashMap<>();

        if (user == null) {
            result.put("flag", "false");
        } else{
            if (!userpsw.equals(inputpassword.trim())){
                result.put("flag", "false");
            }else{
                result.put("flag", "true");
            }
        }
        return result;
    }

    @PostMapping(value="userRegister")
    public int UserRegister(@RequestBody User user, @RequestParam(name = "input_code") String inputCode){
        String emailAddress = user.getEmail();
        String verifyCode = verifyService.getVerifyCode(emailAddress);

        boolean userRegistered = userService.getUser(emailAddress);
        if (userRegistered) return -1;

        boolean status = verifyService.compareCode(inputCode, verifyCode);
        if (!status) return -2;


        List<CartItem> list = new ArrayList<>();
        user.setCart(new Cart(list));

        return userService.saveUser(user);
    }

}

