package com.holdmelol.someshop.repositories;

import com.holdmelol.someshop.entities.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket, Long> {
}
