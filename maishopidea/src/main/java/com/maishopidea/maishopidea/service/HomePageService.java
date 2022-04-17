package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: hzh
 * @Date: 2022/4/15 - 04 - 15 - 13:00
 */
public interface HomePageService {
    Page<Product> getProductPage(Integer page, Integer size);
}
