package com.gahelrodriguez.kinalapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * Utilidad de migración de contraseñas.
 *
 * Sirve para convertir contraseñas en texto plano (que ya existan en la BD)
 * a hashes BCrypt seguros. Se ejecuta una sola vez como programa independiente
 * (tiene su propio main), no es un componente de Spring.
 */
public class PasswordMigrationUtil {

    public static void main(String[] args) {

        // Crea una instancia del encoder BCrypt con la configuración por defecto
        // (fuerza 10). El mismo algoritmo que usa SecurityConfig, así los hashes
        // generados aquí serán compatibles con el login de la aplicación.
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Lista de contraseñas en texto plano que se quieren hashear.
        // Aquí se ponen las contraseñas "originales" que los usuarios tienen
        // guardadas sin cifrar en la base de datos.
        String[] passwordsPlanas = {"1234", "admin", "vendedor", "user123"};

        System.out.println("=== Hashes BCrypt generados ===");
        System.out.println();

        // Por cada contraseña en texto plano, genera su hash(hash algoritmo matemático que transforma cualquier
        // cantidad de datos en una cadena de caracteres alfanuméricos de longitud fija) y muestra
        // la información necesaria para actualizar la base de datos.
        for (String password : passwordsPlanas) {

            // encoder.encode() aplica el algoritmo BCrypt a la contraseña.
            // Cada llamada genera un hash DIFERENTE (BCrypt incluye un "salt"
            // aleatorio), pero todos son válidos para verificar la contraseña original.
            String hash = encoder.encode(password);

            // Muestra la contraseña original (solo para referencia durante la migración)
            System.out.println("Texto plano : " + password);

            // Muestra el hash BCrypt listo para copiar a la BD
            System.out.println("Hash BCrypt : " + hash);

            // Genera el SQL UPDATE listo para ejecutar en la base de datos.
            // Busca el registro donde la contraseña aún está en texto plano
            // y la reemplaza por el hash seguro.
            System.out.println("SQL UPDATE  : UPDATE usuarios SET password = '" + hash + "' WHERE password = '" + password + "';");

            System.out.println();
        }
    }
}