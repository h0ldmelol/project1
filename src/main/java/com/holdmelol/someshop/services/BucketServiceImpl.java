package com.holdmelol.someshop.services;

import com.holdmelol.someshop.entities.Bucket;
import com.holdmelol.someshop.entities.Product;
import com.holdmelol.someshop.entities.User;
import com.holdmelol.someshop.repositories.BucketRepo;
import com.holdmelol.someshop.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService{
    private final BucketRepo bucketRepo;
    private final ProductRepo productRepo;

    public BucketServiceImpl(BucketRepo bucketRepo, ProductRepo productRepo) {
        this.bucketRepo = bucketRepo;
        this.productRepo = productRepo;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepo.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepo::getOne)
                .collect(Collectors.toList());
    }


    @Override
    public void addProducts(Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepo.save(bucket);
    }
}
