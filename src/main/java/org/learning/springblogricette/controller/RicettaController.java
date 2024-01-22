package org.learning.springblogricette.controller;

import jakarta.validation.Valid;
import org.learning.springblogricette.model.Categoria;
import org.learning.springblogricette.model.Ricetta;
import org.learning.springblogricette.repository.CategoriaRepository;
import org.learning.springblogricette.repository.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ricette")
public class RicettaController {

    // REPOSITORY RICETTA
    @Autowired
    private RicettaRepository ricettaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // METODO INDEX CHE MOSTRA LA LISTA DI TUTTE LE RICETTE
    @GetMapping
    public String index(Model model) {
        List<Ricetta> ricettaList = ricettaRepository.findAll();
        model.addAttribute("ricettaList", ricettaList);
        return "ricette/list";
    }

    // METODO SHOW CHE MOSTRA I DETTAGLI DI UNA SINGOLA RICETTA
    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            Ricetta ricetta = result.get();
            model.addAttribute("ricetta", ricetta);
            return "ricette/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricepe with id " + id + " not found");
        }
    }

    // METODO CREATE CHE MOSTRA LA PAGINA COL FORM DI CREAZIONE DI UNA RICETTA
    @GetMapping("/create")
    public String create(Model model) {
        Ricetta ricetta = new Ricetta();
        model.addAttribute("ricetta", ricetta);
        return "ricette/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ricette/create";
        }
        Ricetta savedRicetta = ricettaRepository.save(formRicetta);
        return "redirect:/ricette";
    }

    // METODO CHE RESTITUISCE LA PAGINA DI MODIFICA DELLA RICETTA
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("ricetta", result.get());
            model.addAttribute("categoria", categoriaRepository.findAll());
            return "ricette/edit";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricepe with id" + id + " not found");
        }
    }

    // METODO CHE RICEVE IL SUBMIT DEL FORM DI EDIT
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ricetta") Ricetta formRicetta, BindingResult bindingResult, Model model) {
        Optional<Ricetta> result = ricettaRepository.findById(formRicetta.getId());
        if (result.isPresent()) {
            Ricetta ricettaToEdit = result.get();
            if (bindingResult.hasErrors()) {
                model.addAttribute("categoria", categoriaRepository.findAll());
                return "ricette/edit";
            }
            formRicetta.setImage(ricettaToEdit.getImage());
            Ricetta savedRicetta = ricettaRepository.save(formRicetta);
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricepe with id" + id + " not found");
        }
    }

    // METODO CHE CANCELLA UNA RICETTA PRESA PER ID
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Ricetta> result = ricettaRepository.findById(id);
        if (result.isPresent()) {
            ricettaRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("redirectMessage", result.get().getTitle() + " è stato cancellato!");
            return "redirect:/ricette";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ricepe with id" + id + " not found");
        }
    }
}








