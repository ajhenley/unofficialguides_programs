package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public String index(){
    return "index";
  }

  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping("/secure")
  public String secure(){
    return "secure";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String showRegistrationPage(Model model) {
    model.addAttribute("appUser", new AppUser());
    return "registration";
  }

  @RequestMapping(value="/register", method=RequestMethod.POST)
  public String processRegistrationPage(@Valid @ModelAttribute("user")
                                                AppUser appUser, BindingResult
          result,
                                        Model model) {
    model.addAttribute("appUser", appUser);
    if (result.hasErrors()) {
      return "registration";
    }
    else {
      userService.saveUser(appUser);
      model.addAttribute("message", "User Account Successfully Created");
    }
    return "index";
  }

}


