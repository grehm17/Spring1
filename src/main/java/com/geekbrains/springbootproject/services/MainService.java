package com.geekbrains.springbootproject.services;

import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.repositories.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {
    private MainRepository MainRepository;

    @Autowired
    public void setDiskHibRepository(MainRepository MainRepository) {
        this.MainRepository = MainRepository;
    }

    public Page<Good> getAll(int numb,float from,float to) {
        Page<Good> page = null;
        if(from == 0f && to == 0f) {
            page = MainRepository.findAll(PageRequest.of(numb, 5));
        }else if(from > 0f && to == 0f){
            page = MainRepository.findByCostGreaterThan(PageRequest.of(numb, 5),from);
        }else if(from == 0f && to > 0f){
            page = MainRepository.findByCostLessThan(PageRequest.of(numb, 5),to);
        }else if(from > 0f && to > 0f){
            page = MainRepository.findByCostBetween(PageRequest.of(numb, 5),from,to);
        }
        return page;
    }
}
