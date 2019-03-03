package com.geekbrains.springbootproject.services;

import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.repositories.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.jpa.domain.Specification;
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

    public Page<Good> getAllDyn(int numb, Specification<Good> spec){
        return MainRepository.findAll(spec,PageRequest.of(numb, 5));
    }
    public void updateItem(Good good){
        MainRepository.save(good);
    }

    public Good getItem(long id){
        return MainRepository.findById(id);
    }

    public Good insert(Good good){
        good.setId(0L);
        return MainRepository.save(good);
    }

    public Good alter(Good good){
        return MainRepository.save(good);
    }

    public int delete(Good good){
        MainRepository.deleteById(good.getId());
        return 200;
    }

}
