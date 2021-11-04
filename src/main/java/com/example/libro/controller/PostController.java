/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libro.controller;

import com.example.libro.entity.Post;
import com.example.libro.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author BReyna
 */
@Controller
public class PostController {
   @Autowired
    private PostRepository postRepository;
    @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("menaje", "Bienvenidos Thymeleaf");
        return "index";
    }
    @RequestMapping("/posts")
    public String post(Model model){
        model.addAttribute("posts", postRepository.findAll());
        return "post";
    }
    @RequestMapping("/form")
    public String create(Model model) {
        return "add";
    }
    @RequestMapping("/add")
    public String guardar(@RequestParam String titulo, @RequestParam String descripcion, Model model) {
        Post post = new Post();
        post.setTitulo(titulo);
        post.setDescripcion(descripcion);
        System.out.println("sout:"+post.getTitulo()+"/"+post.getDescripcion());
        postRepository.save(post);
        return "redirect:/posts";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Post post = postRepository.findById(id).orElse(null);
        postRepository.delete(post);
        return "redirect:/posts";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("post", postRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String titulo, @RequestParam String descripcion) {
        Post post = postRepository.findById(id).orElse(null);
        post.setTitulo(titulo);
        post.setDescripcion(descripcion);
        postRepository.save(post);
        return "redirect:/posts";
    }
}