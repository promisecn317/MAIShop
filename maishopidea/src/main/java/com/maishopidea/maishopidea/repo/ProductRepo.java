package com.maishopidea.maishopidea.repo;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    List<Product> findAllBySellable(boolean sellable);

}
