package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.common.Result;
import com.maishopidea.maishopidea.entity.Cart;
import com.maishopidea.maishopidea.entity.CartItem;
import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.entity.User;
import com.maishopidea.maishopidea.service.UserService;
import com.maishopidea.maishopidea.service.VerifyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity userLogin(@RequestBody User user
    ) throws Exception {
        String inputUserEmail=user.getEmail();
        String inputpassword=user.getPassword();
        //User user = userService.userLogin(inputUserEmail, inputpassword);
        try {
            User user1 = userService.findByEmail(inputUserEmail);
            String userpsw = user1.getPassword();
            int userId=user1.getUserId();
            Map<String, String> result = new HashMap<>();
            if (!userpsw.equals(inputpassword.trim())) {
                result.put("flag", "false");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else {
                result.put("flag", "true");
                return ResponseEntity.status(HttpStatus.OK).body(userId);
            }
        } catch (NullPointerException e) {
            Map<String, String> result = new HashMap<>();
            result.put("flag", "false");
            return ResponseEntity.status(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE).body(null);
        }
    }


    @PostMapping(value = "userRegister/{code}")
    public ResponseEntity userRegister (@RequestBody User user, @PathVariable(name = "code") String inputCode){
        String emailAddress = user.getEmail();
        String verifyCode = verifyService.getVerifyCode(emailAddress);

        boolean status = verifyService.compareCode(inputCode, verifyCode);
        if (!status) return new ResponseEntity("Code is wrong",HttpStatus.BAD_REQUEST);

        List<CartItem> list = new ArrayList<>();
        user.setCart(new Cart(list));

        userService.saveUser(user);
        return new ResponseEntity("Register Success.", HttpStatus.OK);
    }
    @PostMapping(value="users")
    public int userRegister(@RequestBody User user){

        List<CartItem> list = new ArrayList<>();
        user.setCart(new Cart(list));

        return userService.saveUser(user);
    }
}
