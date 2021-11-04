/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.libro.controller;

import com.example.libro.entity.Autor;
import com.example.libro.entity.Editorial;
import com.example.libro.entity.libro;
import com.example.libro.repository.AutorRepository;
import com.example.libro.repository.EditorialRepository;
import com.example.libro.repository.libroRepository;
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
public class libroController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private libroRepository LibroRepository;
    
    @Autowired
    private EditorialRepository editorialRepository; 

    @RequestMapping("/libs")
    public String prods(Model model) {
        model.addAttribute("libs", LibroRepository.findAll());
        return "libro";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("auts", autorRepository.findAll());
        model.addAttribute("edis", editorialRepository.findAll());
        return "addLib";
    }
    

    @RequestMapping("/libadd")
    public String guardar(@RequestParam String nom_libro, @RequestParam int idautor, @RequestParam int ideditorial,Model model) {
        libro lib = new libro();
        Autor aut = autorRepository.findById(idautor).get();
        Editorial edi = editorialRepository.findById(ideditorial).get();
        lib.setNom_libro(nom_libro);
        lib.setAutor(aut);
        lib.setEditorial(edi);
        LibroRepository.save(lib);
        return "redirect:/libs";
    }
    @RequestMapping("/dellib/{id}")
    public String deleteprod(@PathVariable(value="id") int id) {
        libro lib = LibroRepository.findById(id).orElse(null);
        LibroRepository.delete(lib);
        return "redirect:/libs";
    }
    @RequestMapping("/editlib/{id}")
    public String edit(@PathVariable(value="id") int id, Model model) {
        model.addAttribute("lib", LibroRepository.findById(id).orElse(null));
        model.addAttribute("auts", autorRepository.findAll());
        model.addAttribute("edis", editorialRepository.findAll());
        
        return "editLib";
    }
    @RequestMapping("/updatelib")
    public String update(@RequestParam int id, @RequestParam String nom_libro, 
             @RequestParam int idautor, @RequestParam int ideditorial) {
        libro lib = LibroRepository.findById(id).orElse(null);
        Autor aut = autorRepository.findById(idautor).get();
        Editorial edi = editorialRepository.findById(ideditorial).get();
        lib.setNom_libro(nom_libro);
        lib.setAutor(aut);
        lib.setEditorial(edi);
        LibroRepository.save(lib);
        return "redirect:/libs";
    }
}
