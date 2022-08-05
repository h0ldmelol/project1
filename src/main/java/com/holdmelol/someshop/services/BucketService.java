package com.holdmelol.someshop.services;

import com.holdmelol.someshop.entities.Bucket;
import com.holdmelol.someshop.entities.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);
}
