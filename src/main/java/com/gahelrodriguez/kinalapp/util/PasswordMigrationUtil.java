package com.gahelrodriguez.kinalapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class PasswordMigrationUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Agrega aquí las contraseñas de texto plano que necesites hashear
        String[] passwordsPlanas = {"1234", "admin", "vendedor", "user123"};

        System.out.println("=== Hashes BCrypt generados ===");
        System.out.println();

        for (String password : passwordsPlanas) {
            String hash = encoder.encode(password);
            System.out.println("Texto plano : " + password);
            System.out.println("Hash BCrypt : " + hash);
            System.out.println("SQL UPDATE  : UPDATE usuarios SET password = '" + hash + "' WHERE password = '" + password + "';");
            System.out.println();
        }
    }
}