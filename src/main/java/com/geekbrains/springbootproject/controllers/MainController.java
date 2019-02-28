package com.geekbrains.springbootproject.controllers;


import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    private MainService MainService;

    @Autowired
    public void setMainService(MainService MainService) {
        this.MainService = MainService;
    }

    @GetMapping("/")
    public String showFirstPage(Model model) {
        return "redirect:/0/params";
    }

    @GetMapping("/{numb}/params*")
    public String showPages(Model model, @PathVariable(value="numb") int numb, @RequestParam Map<String, String> paramsMap) {
        Double from = 0d;
        Double to = 0d;
        if (!paramsMap.isEmpty()){
            if(paramsMap.containsKey("from")){from = Double.parseDouble(paramsMap.get("from"));}
            if(paramsMap.containsKey("to")){to = Double.parseDouble(paramsMap.get("to"));}
        }
        Page page = MainService.getAll(numb,from,to);
        int totalPg = page.getTotalPages();
        model.addAttribute("goods",page);
        model.addAttribute("numb",numb);
        model.addAttribute("totalPg",totalPg);
        if (!paramsMap.isEmpty()){
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    model.addAttribute(entry.getKey(),entry.getValue());
            }
        }
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
        return "redirect:/0/params";
    }

    @PostMapping("/filter")
    public String setFilter(Model model, @RequestParam Map<String, String> paramsMap){
        return "redirect:/0/params"+buildParams(paramsMap);
    }

    private String buildParams(Map<String, String> paramsMap){
        StringBuilder paramsString = new StringBuilder();
        if (!paramsMap.isEmpty()){
            paramsString.append("?");
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                if (!entry.getValue().equals("")){
                    paramsString.append("&" + entry.getKey() + "="+entry.getValue());
                }
            }
        }
        return paramsString.toString();
    }

}
