package com.gahelrodriguez.kinalapp.controller;

import com.gahelrodriguez.kinalapp.entity.Usuario;
import com.gahelrodriguez.kinalapp.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistroController(UsuarioRepository usuarioRepository,
                              PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Muestra el formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "registro";
    }

    // Procesa el formulario de registro
    @PostMapping("/registro")
    public String procesarRegistro(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String rol,
            Model model) {

        // Verificar si el username ya existe
        if (usuarioRepository.findByUsername(username).isPresent()) {
            model.addAttribute("error", "El username '" + username + "' ya está en uso.");
            return "registro";
        }

        if (!rol.equals("USER") && !rol.equals("VENDEDOR")) {
            model.addAttribute("error", "Rol no válido.");
            return "registro";
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(passwordEncoder.encode(password));
        nuevo.setEmail(email);
        nuevo.setRol(rol);
        nuevo.setEstado(1L); // activo por defecto

        usuarioRepository.save(nuevo);

        // Redirige al login con parámetro de éxito
        return "redirect:/login?registrado";
    }
}