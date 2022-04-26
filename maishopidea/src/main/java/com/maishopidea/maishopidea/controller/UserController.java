package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.common.Result;
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
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    private VerifyStatusService verifyService;

    @Autowired
    public void setVerifyService(VerifyStatusService verifyService) {
        this.verifyService = verifyService;
    }

    @PostMapping(value = "login")
    @ResponseBody
    public Result<?> userLogin(@RequestParam(name = "userEmail") String inputUserEmail,
                               @RequestParam("userPassword") String inputpassword
    ) throws Exception {
        //User user = userService.userLogin(inputUserEmail, inputpassword);
        try {
            User user = userService.findByEmail(inputUserEmail);
            String userpsw = user.getPassword();
            Map<String, String> result = new HashMap<>();
            if (!userpsw.equals(inputpassword.trim())) {
                result.put("flag", "false");
                return Result.error("204", "Incorrect username or password");
            } else {
                result.put("flag", "true");
                return Result.success();
            }
        } catch (NullPointerException e) {
            Map<String, String> result = new HashMap<>();
            result.put("flag", "false");
            return Result.error("204", "User does not exist");
        }
    }


        @PostMapping(value = "userRegister")
        public int userRegister (@RequestBody User user, @RequestParam(name = "input_code") String inputCode){
            String emailAddress = user.getEmail();
            String verifyCode = verifyService.getVerifyCode(emailAddress);

            boolean status = verifyService.compareCode(inputCode, verifyCode);
            if (!status) return -2;

            List<CartItem> list = new ArrayList<>();
            user.setCart(new Cart(list));

            return userService.saveUser(user);
        }
}

