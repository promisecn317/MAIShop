package com.maishopidea.maishopidea.repo;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Auther: hzh
 * @Date: 2022/4/15 - 04 - 15 P 12:59
 */
@Repository     //JpaRepository<Category,Integer>
public interface HomepageRepo extends JpaRepository<Product,Integer> {
    List<Product> findByProductIdAndDescriptionAndProductPriceAndProductNameAndCreateDateAndProductQty
            (int productId, String description, double productPrice, String productName, Date createDate, int productQty);
    //JpaRepository<Category,Integer>,这里的Integer指的是ID的类型是int，JpaRepository<>里对应就是哪一个实体类跟实体类ID对应的类型
    //在springboot中不需要实现类，JPA会提供implementation
    public List<Product> findAllBySellable(boolean sellable);
}
