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

    @RequestMapping(value="search")
    public ResponseEntity<List<Product>> searchProduct(String productName){
        List<Product>products= searchService.findAllByProductNameContaining(productName);
        return products==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(products );
    }



//    @RequestMapping("search")
//    public String search(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
//                         Model model,
//                         Search newsSearch){
//        Page<News> page = newsService.searchNews(pageable, newsQuery);
//        model.addAttribute("page", page);
//        return "admin/news :: newsList";
//    }


}
