package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();
}
