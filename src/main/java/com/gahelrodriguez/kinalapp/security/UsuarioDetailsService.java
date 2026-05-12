package com.gahelrodriguez.kinalapp.security;

import com.gahelrodriguez.kinalapp.entity.Usuario;
import com.gahelrodriguez.kinalapp.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UsuarioDetailsService: puente entre Spring Security y nuestra BD.
 *
 * Spring Security llama a loadUserByUsername() en cada intento de login.
 * Este servicio busca el usuario, verifica que esté activo (estado == 1)
 * y construye el objeto UserDetails con su rol para el control de acceso.
 *
 * Convención de roles en Spring Security:
 *   - En BD guardamos: "ADMIN", "VENDEDOR", "USER"
 *   - Spring Security espera el prefijo "ROLE_": "ROLE_ADMIN", etc.
 *   - hasRole("ADMIN") busca internamente "ROLE_ADMIN"
 *   - Por eso usamos "ROLE_" + usuario.getRol() al crear la authority
 */
@Service
@Transactional(readOnly = true)
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. Buscar usuario en BD por username
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username));

        // 2. Verificar que el usuario esté activo (estado == 1)
        if (usuario.getEstado() == null || usuario.getEstado() != 1L) {
            throw new UsernameNotFoundException(
                    "Usuario inactivo: " + username);
        }

        // 3. Construir la authority con el prefijo ROLE_
        //    Ejemplo: "ADMIN" → "ROLE_ADMIN"
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + usuario.getRol());

        // 4. Retornar UserDetails con username, password (BCrypt) y roles
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // debe estar en BCrypt en BD
                .authorities(List.of(authority))
                .build();
    }
}