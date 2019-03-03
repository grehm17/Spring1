package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Good;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository extends PagingAndSortingRepository<Good, Long>, JpaSpecificationExecutor{
    Good findById(long id);
}
