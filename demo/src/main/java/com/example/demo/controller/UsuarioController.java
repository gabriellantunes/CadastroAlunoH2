package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/processLogin")
    public String login(@RequestParam String email, @RequestParam String senha) {
        Usuario usuario = usuarioService.findByEmail(email).orElse(null);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return "dashboard";
        }
        return "login";
    }

    @GetMapping("/cadastro")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/doCadastro")
    public String register(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam int idade,
            @RequestParam String genero,
            @RequestParam String endereco,
            @RequestParam String estadoCivil,
            @RequestParam String telefone) {

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setIdade(idade);
        usuario.setGenero(genero);
        usuario.setEndereco(endereco);
        usuario.setEstadoCivil(estadoCivil);
        usuario.setTelefone(telefone);

        usuarioService.save(usuario);

        return "login";
    }

}
