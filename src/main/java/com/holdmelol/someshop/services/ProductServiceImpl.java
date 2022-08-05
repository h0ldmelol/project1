package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.ProductDTO;
import com.holdmelol.someshop.mapper.ProductMapper;
import com.holdmelol.someshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepo.findAll());
    }
}
