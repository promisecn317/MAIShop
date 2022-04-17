package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SearchService {
    /**
     * deviceNames模糊查询
     * @param productName productName
     * @return {@link List<Product>}
     */
    List<Product> findAllProduct(String productName);
    List<Product> searchProductSellable(String productName);
}
