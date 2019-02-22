package com.geekbrains.springbootproject.repositories;

import com.geekbrains.springbootproject.entities.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainRepository extends PagingAndSortingRepository<Good, Long> {
    Page<Good> findByCostGreaterThan(Pageable pageable, float fromCost);
    Page<Good> findByCostLessThan(Pageable pageable,float toCost);
    Page<Good> findByCostBetween(Pageable pageable,float fromCost,float toCost);
}
