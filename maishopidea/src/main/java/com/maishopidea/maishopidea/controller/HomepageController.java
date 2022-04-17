package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: hzh
 * @Date: 2022/4/15 - 04 - 15 - 12:58
 */
public class HomepageController {
    @Autowired
    private HomePageService homepageService;
    
    @RequestMapping("/index")//分页查询
    public Page<Product> toIndex(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Page<Product> productions = homepageService.getProductPage(page, size);
        model.addAttribute("production", productions);
        return homepageService.getProductPage(page, size);
    }

}
