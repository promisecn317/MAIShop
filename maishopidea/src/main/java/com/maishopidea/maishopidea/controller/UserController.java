package com.maishopidea.maishopidea.controller;


import com.maishopidea.maishopidea.entity.User;
import com.maishopidea.maishopidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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


}

