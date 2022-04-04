package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {

    public List<Product> getSellable();

    public Product getProduct(int productId);

    public Product saveProduct(Product product);    //product to return product information


}
