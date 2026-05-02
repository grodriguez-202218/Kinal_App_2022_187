package com.gahelrodriguez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarLogin(
            @RequestParam(value = "error",      required = false) String error,
            @RequestParam(value = "logout",     required = false) String logout,
            @RequestParam(value = "registrado", required = false) String registrado,
            Model model) {

        if (error != null) {
            model.addAttribute("error",
                    "Usuario o contraseña incorrectos, o cuenta inactiva.");
        }
        if (logout != null) {
            model.addAttribute("logoutMsg",
                    "Sesión cerrada correctamente. ¡Hasta pronto!");
        }
        if (registrado != null) {
            model.addAttribute("registradoMsg",
                    "¡Cuenta creada exitosamente! Ya puedes iniciar sesión.");
        }

        return "login";
    }
}