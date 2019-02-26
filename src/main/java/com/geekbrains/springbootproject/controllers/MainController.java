package com.geekbrains.springbootproject.controllers;


import com.geekbrains.springbootproject.entities.Good;
import com.geekbrains.springbootproject.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private MainService MainService;
    private double from = 0;
    private double to = 0;

    @Autowired
    public void setMainService(MainService MainService) {
        this.MainService = MainService;
    }

    @GetMapping("/")
    public String showFirstPage(Model model) {
        return "redirect:/0";
    }

    @GetMapping("/{numb}")
    public String showPages(Model model, @PathVariable(value="numb") int numb) {
        Page page = MainService.getAll(numb,from,to);
        int totalPg = page.getTotalPages();
        model.addAttribute("goods",page);
        model.addAttribute("numb",numb);
        model.addAttribute("totalPg",totalPg);
        model.addAttribute("from",from);
        model.addAttribute("to",to);
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
        return "redirect:/0";
    }

    @PostMapping("/filter")
    public String setFilter(Model model, @RequestParam(value = "from") String from, @RequestParam(value = "to") String to){
        this.from = Double.parseDouble(from == "" ? "0" : from);
        this.to = Double.parseDouble(to == "" ? "0" : to);
        return "redirect:/0";
    }

}
