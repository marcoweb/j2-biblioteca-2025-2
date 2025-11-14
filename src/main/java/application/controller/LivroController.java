package application.controller;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import application.model.Autor;
import application.model.Genero;
import application.model.Livro;
import application.repository.AutorRepository;
import application.repository.GeneroRepository;
import application.repository.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepo;
    @Autowired
    private GeneroRepository generoRepo;
    @Autowired
    private AutorRepository autorRepo;

    @RequestMapping("/list")
    public String list(Model ui) {
        ui.addAttribute("livros", livroRepo.findAll());
        return "/livros/list";
    }

    @RequestMapping("/insert")
    public String insert(Model ui) {
        ui.addAttribute("generos", generoRepo.findAll());
        ui.addAttribute("autores", autorRepo.findAll());
        return "/livros/insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(
        @RequestParam("titulo") String titulo,
        @RequestParam("genero_id") long generoId,
        @RequestParam("autores") long[] autoresIds) {

        Optional<Genero> generoResult = generoRepo.findById(generoId);

        if(generoResult.isPresent()) {
            Livro livro = new Livro();
            livro.setTitulo(titulo);
            livro.setGenero(generoResult.get());

            for(long a_id : autoresIds) {
                Optional<Autor> resultAutor = autorRepo.findById(a_id);
                if(resultAutor.isPresent()) {
                    livro.getAutores().add(resultAutor.get());
                }
            }

            livroRepo.save(livro);
        }
        
        return "redirect:/livros/list";
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable long id, Model ui) {
        ui.addAttribute("livro", livroRepo.findById(id).get());
        ui.addAttribute("generos", generoRepo.findAll());
        ui.addAttribute("autores", autorRepo.findAll());
        return "/livros/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(
        @RequestParam("id") long id,
        @RequestParam("titulo") String titulo,
        @RequestParam("genero_id") long generoId,
        @RequestParam("autores") long[] autoresIds) {

        Optional<Livro> livroResult = livroRepo.findById(id);
        Optional<Genero> generoResult = generoRepo.findById(generoId);

        if(livroResult.isPresent() && generoResult.isPresent()) {
            livroResult.get().setTitulo(titulo);
            livroResult.get().setGenero(generoResult.get());
            livroResult.get().setAutores(new HashSet<Autor>());

            for(long a_id : autoresIds) {
                Optional<Autor> resultAutor = autorRepo.findById(a_id);
                if(resultAutor.isPresent()) {
                    livroResult.get().getAutores().add(resultAutor.get());
                }
            }

            livroRepo.save(livroResult.get());
        }

        return "redirect:/livros/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model ui) {
        ui.addAttribute("livro", livroRepo.findById(id).get());
        return "/livros/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") long id) {
        livroRepo.deleteById(id);
        return "redirect:/livros/list";
    }
}
