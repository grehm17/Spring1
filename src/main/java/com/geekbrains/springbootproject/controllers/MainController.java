package com.geekbrains.springbootproject.controllers;


import com.geekbrains.springbootproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    private MainService MainService;

    @Autowired
    public void setMainService(MainService MainService) {
        this.MainService = MainService;
    }

    @GetMapping("/")
    public String showFirstPage(Model model) {
        model.addAttribute("goods", MainService.getAll(0,0,0));
        model.addAttribute("numb",0);
        model.addAttribute("from",0);
        model.addAttribute("to",0);
        return "index";
    }

    @GetMapping("/{numb}/{from}/{to}")
    public String showPages(Model model, @PathVariable(value="numb") int numb,@PathVariable(value="from") int from,@PathVariable(value="to") int to) {
        if(numb < 0){
            numb = 0;
        }
        model.addAttribute("goods",MainService.getAll(numb,from,to));
        model.addAttribute("numb",numb);
        model.addAttribute("from",from);
        model.addAttribute("to",to);
        return "index";
    }
}
