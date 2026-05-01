package com.gahelrodriguez.kinalapp.controller;

import com.gahelrodriguez.kinalapp.entity.Usuario;
import com.gahelrodriguez.kinalapp.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final IUsuarioService usuarioService;

    public LoginController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Muestra el formulario de login
    @GetMapping("/login")
    public String mostrarLogin(HttpSession session, Model model) {
        // Si ya hay sesión activa, redirigir al inicio
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/";
        }
        return "login";
    }

    // Procesa el formulario de login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorUsername(username);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            // Verificamos usuario activo y contraseña correcta
            if (usuario.getEstado() == 1 && usuario.getPassword().equals(password)) {
                // Guardamos datos del usuario en sesión
                session.setAttribute("usuarioLogueado", usuario);
                session.setAttribute("usernameLogueado", usuario.getUsername());
                session.setAttribute("rolLogueado", usuario.getRol());
                // La sesión expira en 30 minutos de inactividad
                session.setMaxInactiveInterval(30 * 60);
                return "redirect:/";
            }
        }

        // Si las credenciales son incorrectas o el usuario está inactivo
        model.addAttribute("error", "Usuario o contraseña incorrectos, o cuenta inactiva.");
        return "login";
    }

    // Cierra la sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}