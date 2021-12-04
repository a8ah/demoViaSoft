package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface EntityRepository<T> extends JpaRepository<T, UUID>/*, JpaSpecificationExecutor<T>, ProjectionPaginationExecutor<T> */{
}
