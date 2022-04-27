package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface ProductService {
    List<Product> getSellable();

    Product getProduct(int productId);

    Product saveProduct(Product product);    //product to return product information

    List<Product> getSellerProductsById(int userId);

}
