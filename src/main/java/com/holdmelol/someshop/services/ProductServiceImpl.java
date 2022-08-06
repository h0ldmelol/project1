package com.holdmelol.someshop.services;

import com.holdmelol.someshop.dto.ProductDTO;
import com.holdmelol.someshop.entities.Bucket;
import com.holdmelol.someshop.entities.User;
import com.holdmelol.someshop.mapper.ProductMapper;
import com.holdmelol.someshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepo productRepo;
    private final UserService userService;
    private final BucketService bucketService;

    public ProductServiceImpl(ProductRepo productRepo, UserService userService, BucketService bucketService) {
        this.productRepo = productRepo;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepo.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Bucket bucket = user.getBucket();
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }
    }
}
