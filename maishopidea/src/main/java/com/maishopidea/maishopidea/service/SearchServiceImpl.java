package com.maishopidea.maishopidea.service;

import com.maishopidea.maishopidea.entity.Product;
import com.maishopidea.maishopidea.repo.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
@Autowired SearchRepo  searchRepo;

    @Override
    public List<Product> findAllByProductNameContaining(String productName){
        return searchRepo.findAllByProductNameContaining(productName);
    }


}
