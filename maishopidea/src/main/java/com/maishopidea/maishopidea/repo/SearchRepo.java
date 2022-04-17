package com.maishopidea.maishopidea.repo;

import com.maishopidea.maishopidea.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository   //JpaRepository<Product,Integer> ,
public interface SearchRepo extends CrudRepository<Product, String> {

    List<Product> findAllByProductNameContaining(String productName);

    List<Product> findAllByProductNameContainingAndSellableTrue(String productName);


}

