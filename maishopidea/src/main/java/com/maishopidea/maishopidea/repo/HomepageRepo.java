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

}
