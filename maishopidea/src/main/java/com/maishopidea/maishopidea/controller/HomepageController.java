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

    @GetMapping(value = "getProduct")
    public ResponseEntity<List<Product>> getProducts(){//查询全部
//        return (ResponseEntity<List<product>>) productService.findAll();
        List<Product> products = homepageService.getSellableProducts();
        return products ==null? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
                :ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "product/{productid}")
    public ResponseEntity<Product> getProduct(@PathVariable("productid") int productId){ //query（按照id查询）
        //Optional<product> prod = productRepo.findById(productId);
        //return prod.isPresent()? prod.get():null;
        Product product = homepageService.getProduct(productId);
        return product == null? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
                :ResponseEntity.ok().body(product);
    }

    @PostMapping(value = "product") //post request
    public ResponseEntity<Integer> newProduct(@RequestBody Product prod){//创建一个新的product 通过“prod” 在数据库中
        //productRepo.save(prod);

        //return prod.getProductId();
        //return productRepo.save(prod).getProductId();
        Integer productId = homepageService.saveProduct(prod);
        return productId == null? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
                :ResponseEntity.ok().body(productId);
    }

    @RequestMapping("/index")//分页查询
    public Page<Product> toIndex(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "3") Integer size) {
        Page<Product> productions = homepageService.getProductPage(page, size);
        model.addAttribute("production", productions);
        return homepageService.getProductPage(page, size);
    }

}
