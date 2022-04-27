package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired ProductRepo productRepo;

    @Override
    public  List<Product> getSellable() {
        return productRepo.findAllBySellable(true);
    }

    @Override
    public Product getProduct(int productId) {
        Optional<Product> product=productRepo.findById(productId);
        return product.orElse(null);
    }

    @Override
    public  Product saveProduct(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public List<Product> getSellerProductsById(int userId) {
        return productRepo.findAllByUserId(userId);
    }

}
