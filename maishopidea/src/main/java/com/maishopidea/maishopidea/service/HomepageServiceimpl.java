package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.repo.HomepageRepo;
import com.maishopidea.maishopidea.repo.ProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Auther: hzh
 * @Date: 2022/4/15 - 04 - 15 - 13:00
 */
@Service
@Slf4j
public class HomepageServiceimpl implements HomePageService {
    @Autowired
    private HomepageRepo homepageRepo;//service层调用dao层接口

    @Override
    public Page<Product> getProductPage(Integer page, Integer size) {
        log.info("page is {}, size is {}", page, size); //生成日志
        if (page <= 0) {
            page = 1;
        }
        Pageable pageRequest = PageRequest.of(page - 1, size);//PageRequest对象代表了查询的条件和约束
        return homepageRepo.findAll(pageRequest);//需要使用PageRequest.of()来获取Pageable对象，因为该对象使用了静态方法代替了构造器
    }
}
