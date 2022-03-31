package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.product;
import com.maishopidea.maishopidea.repo.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class productContorller {
    @Autowired
    private productRepo productRepo;

    @PostMapping(value = "productInformation")

    public product saveInformation(@RequestPart("product") product product, @RequestParam(name="productImage",required = false)
            MultipartFile productImage) throws IOException {
        product product1=new product();
        product1.setProductId(product.getProductId());
        product1.setProductName(product.getProductName());
        product1.setProductDescription(product.getProductDescription());
        product1.setCreatedDate(product.getCreatedDate());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductQty(product.getProductQty());
        product1.setSellable(true);

        if(productImage==null)
        {
            product1.setProductImage(null);
        }else {
            product1.setProductImage(productImage.getBytes());//.getBytes()
        }
        productRepo.save(product1);

        return product1;

    }



}
