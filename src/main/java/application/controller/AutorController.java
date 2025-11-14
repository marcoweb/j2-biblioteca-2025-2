package application.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Autor;
import application.repository.AutorRepository;

@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorRepository autorRepo;

    @RequestMapping("/insert")
    public String insert() {
        return "/autores/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome) {
        Autor autor = new Autor();
        autor.setNome(nome);
        autorRepo.save(autor);
        return "redirect:/autores/list";
    }

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("autores", autorRepo.findAll());
        return "/autores/list";
    }

    @RequestMapping("/list/{id}")
    public String listOne(@PathVariable long id, Model ui) {
        Optional<Autor> autorResult = autorRepo.findById(id);
        if(autorResult.isPresent()) {
            ui.addAttribute("autor", autorResult.get());
            return "/autores/listOne";
        }
        return "redirect:/autores/list";
    }
}
