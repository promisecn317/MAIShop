package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;


    // 搜索所有product 包括已经售出的
    @RequestMapping(value = "search")
    public ResponseEntity<List<Product>> searchProduct(String productName) {
        List<Product> products = searchService.findAllProduct(productName);
        return products == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok().body(products);
    }


    // 如果已售出则不显示
    @RequestMapping(value = "searchProductSellable")
    public ResponseEntity<List<Product>> searchProductSellable(String productName) {
        List<Product> products = searchService.searchProductSellable(productName);
        return products == null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok().body(products);
    }

    //分页查询


    //判断多个keywords的排序


    //仅返回name   price  image

    //test

//    @RequestMapping("search")
//    public String search(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
//                         Model model,
//                         Search newsSearch){
//        Page<News> page = searchService.(pageable, searchService);
//        model.addAttribute("page", page);
//        return "admin/news :: newsList";
//    }


}
