package com.maishopidea.maishopidea.controller;

import com.maishopidea.maishopidea.common.Result;
import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.service.ProductService;
import com.maishopidea.maishopidea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductContorller {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping(value="getsellable")
    public ResponseEntity<List<Product> > getSellableProduct(){
        List<Product>products= productService.getSellable();
        return products==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(products );
    }

    @GetMapping(value="getproduct")
    public ResponseEntity<Product> getProduct(int productId) {
        Product product= productService.getProduct(productId);
        return product==null ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null):ResponseEntity.ok().body(product);
    }

    @PostMapping(value = "saveProductInformation")
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

    @PostMapping(value = "updateProductInformation")
    public void updateProductInformation(@RequestPart("prod") Product prod, @RequestParam(name="productImage",required=false) MultipartFile productImage) throws IOException {
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

    @PostMapping(value = "my_product")
    public Result<List<Product>> myProductList(@RequestParam(name = "userEmail") String userEmail) throws Exception {
        int userId = userService.findByEmail(userEmail).getUserId();
        List<Product> sellerProducts = productService.getProductsByUserId(userId);
        return Result.success(sellerProducts);
    }

}
