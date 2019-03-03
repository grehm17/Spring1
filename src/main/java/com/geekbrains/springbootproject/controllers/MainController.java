package com.geekbrains.springbootproject.controllers;


import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.repositories.Specs.GoodSpec;
import com.geekbrains.springbootproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    private MainService MainService;

    @Autowired
    private Environment env;

    @Autowired
    public void setMainService(MainService MainService) {
        this.MainService = MainService;
    }

    @GetMapping("/*")
    public String showPages(Model model,@RequestParam Map<String, String> paramsMap) {
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
                String fieldName = env.getProperty(key);
                if (fieldName != null){
                    spec = spec.and(GoodSpec.createSpec(env.getProperty(key+"SpecType"),fieldName,value));
                    model.addAttribute(entry.getKey(),entry.getValue());
                }
            }
        }
        Page page = MainService.getAllDyn(numb,spec);
        int totalPg = page.getTotalPages();
        model.addAttribute("goods",page);
        model.addAttribute("numb",numb);
        model.addAttribute("totalPg",totalPg);
        model.addAttribute("params",buildParams(paramsMap));
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String setChanges(Model model, @PathVariable(value="id") long id) {
        Good good = MainService.getItem(id);
        model.addAttribute("good",good);
        return "edit";
    }

    @PostMapping("/apply")
    public String applyChanges(Model model,  @ModelAttribute("good") Good good){
        MainService.updateItem(good);
        return "redirect:/?numb=0";
    }

    @PostMapping("/filter")
    public String setFilter(Model model, @RequestParam Map<String, String> paramsMap){
        return "redirect:/?numb=0"+buildParams(paramsMap);
    }

    private String buildParams(Map<String, String> paramsMap){
        StringBuilder paramsString = new StringBuilder();
        if (!paramsMap.isEmpty()){
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                if (!entry.getValue().equals("")){
                    paramsString.append("&" + entry.getKey() + "="+entry.getValue());
                }
            }
        }
        return paramsString.toString();
    }

}
