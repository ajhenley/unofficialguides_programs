package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String homePage(Model model){
    model.addAttribute("myvar", "Say hello to the people.");
    return "hometemplate";
  }
}
