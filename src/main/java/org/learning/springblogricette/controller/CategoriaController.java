package org.learning.springblogricette.controller;

import jakarta.validation.Valid;
import org.learning.springblogricette.model.Categoria;
import org.learning.springblogricette.repository.CategoriaRepository;
import org.learning.springblogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/categorie")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private RicettaRepository ricettaRepository;

    // METODO INDEX CHE MOSTRA LA LISTA DI TUTTE LE CATEGORIE
    @GetMapping
    public String index(Model model){
        List<Categoria> categoryList;
        categoryList= categoriaRepository.findAll();
        model.addAttribute("categoria", categoryList);
        return "categorie/list";
    }
    // METODO CREATE CHE MOSTRA LA PAGINA COL FORM DI CREAZIONE DI UNA CATEGORIA
    @GetMapping("/create")
    public String create(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "categorie/create";
    }
    @PostMapping("/create")
    public String category(@Valid @ModelAttribute("categoria") Categoria formCategoria, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoria", categoriaRepository.findAll());
            return "categorie/create";
        }
        Categoria savedCategoria = categoriaRepository.save(formCategoria);
        return "redirect:/categorie";
    }
    // METODO CHE RESTITUISCE LA PAGINA DI MODIFICA DELLA CATEGORIA
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Categoria> result = categoriaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("categoria", result.get());
            return "categorie/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }
    // METODO CHE RICEVE IL SUBMIT DEL FORM DI EDIT
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("categoria") Categoria formcategoria, BindingResult bindingResult) {
        Optional<Categoria> result = categoriaRepository.findById(id);
        if (result.isPresent()) {
            Categoria categoriaToEdit = result.get();
            if (bindingResult.hasErrors()) {
                return "categorie/edit";
            }
            Categoria savedCategoria = categoriaRepository.save(formcategoria);
            return "redirect:/categorie";

        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "category with id " + id + " not found");
        }
    }
    // METODO CHE CANCELLA UNA CATEGORIA PRESA PER ID
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Categoria> result = categoriaRepository.findById(id);
        if (result.isPresent()) {
            Categoria categoriaToDelete = result.get();
            categoriaRepository.delete(categoriaToDelete);
            return "redirect:/categorie";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Category with id " + id + " not found");
        }
    }
}
