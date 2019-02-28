package com.geekbrains.springbootproject.services;

import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.repositories.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class MainService {
    private MainRepository MainRepository;

    @Autowired
    public void setDiskHibRepository(MainRepository MainRepository) {
        this.MainRepository = MainRepository;
    }

    public Page<Good> getAll(int numb,double from,double to) {
        Page<Good> page = null;
        if(from == 0d && to == 0d) {
            page = MainRepository.findAll(PageRequest.of(numb, 5));
        }else if(from > 0d && to == 0d){
            page = MainRepository.findByCostGreaterThan(PageRequest.of(numb, 5),from);
        }else if(from == 0d && to > 0d){
            page = MainRepository.findByCostLessThan(PageRequest.of(numb, 5),to);
        }else if(from > 0d && to > 0d){
            page = MainRepository.findByCostBetween(PageRequest.of(numb, 5),from,to);
        }
        return page;
    }

    public void updateItem(Good good){
        MainRepository.save(good);
    }

    public Good getItem(long id){
        return MainRepository.findById(id);
    }
}
