package com.project.project.web.client.controller;

import com.project.project.web.client.dto.RegisterDTO;
import com.project.project.web.client.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AuthenticationController {

    @Autowired
    private RestTemplate restTemplate;

    private String url = "http://localhost:8081/autenticacion/";

    @GetMapping("/registrarse")
    public String registrarse(Model model){
        model.addAttribute("registerDTO", new RegisterDTO());
        return "authentication/Registrarse";
    }

    @PostMapping("/registrarse")
    public String registrarse(@ModelAttribute RegisterDTO registerDTO, Model model) {

        ResponseEntity<String> respuesta = restTemplate.postForEntity(url + "registrarse", registerDTO, String.class);
        model.addAttribute("mensaje", respuesta.getBody());
        return "authentication/Inicio";
    }

    @GetMapping()
    public String iniciarSesion(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "authentication/IniciarSesion";
    }

    @PostMapping()
    public String iniciarSesion(@ModelAttribute UserDTO userDTO, Model model) {

        ResponseEntity<String> respuesta = restTemplate.postForEntity(url + "iniciarSesion", userDTO, String.class);
        model.addAttribute("mensaje", respuesta.getBody());
        return "authentication/Inicio";
    }
}
