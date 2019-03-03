package com.geekbrains.springbootproject.controllers;

import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.repositories.Specs.GoodSpec;
import com.geekbrains.springbootproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class MainRestController {
    @Autowired
    private MainService mainServ;

    @Autowired
    private Environment env;

    @GetMapping("/goods/*")
    public Page<Good> showPages(@RequestParam Map<String, String> paramsMap) {
        Specification<Good> spec = Specification.where(null);
        int numb = 0;
        if (!paramsMap.isEmpty()){
            if (paramsMap.containsKey("numb")){
                numb = Integer.parseInt(paramsMap.get("numb"));
                paramsMap.remove("numb");
            }
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!entry.getValue().equals("")){
                String fieldName = env.getProperty(key);
                if (fieldName != null){
                    spec = spec.and(GoodSpec.createSpec(env.getProperty(key+"SpecType"),fieldName,value));
                }}
            }
        }
        return mainServ.getAllDyn(numb,spec);
    }

    @PostMapping("/goods")
    public Good insertGood(@RequestBody Good good) {
        System.out.println(good);
        return mainServ.insert(good);
    }

    @PutMapping("/goods")
    public Good alterGood(@RequestBody Good good) {
        return mainServ.alter(good);
    }

    @DeleteMapping("/goods")
    public int deleteGood(@RequestBody Good good) {
        return mainServ.delete(good);
    }

}
