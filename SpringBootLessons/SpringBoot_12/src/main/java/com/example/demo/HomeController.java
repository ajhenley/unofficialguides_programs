package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

  @Autowired
  ActorRepository actorRepository;

  @Autowired
  CloudinaryConfig cloudc;

  @RequestMapping("/")
  public String listActors(Model model){
    model.addAttribute("actors", actorRepository.findAll());
    return "list";
  }

  @GetMapping("/add")
  public String newActor(Model model){
    model.addAttribute("actor", new Actor());
    return "form";
  }

  @PostMapping("/add")
  public String processActor(@ModelAttribute Actor actor,
                             @RequestParam("file")MultipartFile file){
    if (file.isEmpty()){
      return "redirect:/add";
    }
    try {
      Map uploadResult =  cloudc.upload(file.getBytes(),
              ObjectUtils.asMap("resourcetype", "auto"));
      actor.setHeadshot(uploadResult.get("url").toString());
      actorRepository.save(actor);
    } catch (IOException e){
      e.printStackTrace();
      return "redirect:/add";
    }
    return "redirect:/";
  }

}
