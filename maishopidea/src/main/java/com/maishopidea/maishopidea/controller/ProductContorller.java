package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.repo.ProductRepo;
import com.maishopidea.maishopidea.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductContorller {
//    @Autowired
//    private ProductRepo productRepo;

    @Autowired
    private ProductService productService;

    @GetMapping(value="getsellable")
    public ResponseEntity<List<Product> > getSellableProduct(){
        List<Product>products= productService.getSellable();
        return products==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(products );
        //instead just return the result of service, we return the result in the body of responseEntity

    }
    @GetMapping(value="getproduct")
    public ResponseEntity<Product> getProduct(int productId) {
        Product product= productService.getProduct(productId);
        return product==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(product);

    }

    @PostMapping(value = "productInformation")
    public ResponseEntity<Product> saveInformation(@RequestPart("product") Product product, @RequestParam(name="productImage",required = false)
            MultipartFile productImage) throws IOException {
        Product product1=new Product();
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
        productService.saveProduct(product1);

        return product==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(product1);
    }

    @PostMapping(value = "image")
    public void getImage(@RequestPart("prod") Product prod,@RequestParam(name="productImage",required=false) MultipartFile productImage) throws IOException {
        Product product=new Product();
        product.setProductName(prod.getProductName());

        product.setProductPrice(prod.getProductPrice());
        product.setProductQty(prod.getProductQty());
        product.setSellable(prod.isSellable());
        product.setProductDescription(prod.getProductDescription());
        product.setCreatedDate(prod.getCreatedDate());
        if (productImage==null)product.setProductImage(null);
        else product.setProductImage(productImage.getBytes());
        productService.saveProduct(product);

    }



}
