## KinalApp_2022187
API REST desarrollada con Spring Boot para la gestión de clientes, usuarios, productos, ventas y detalle de ventas.
## Tecnologías
* **java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL** (Sistema Gestor de Base de Datos)

## Requisitos Previos
Antes de ejecutar la aplicación, debe tener instalado:
* JDK 21 o superior
* Maven Instalado
* Una instancia activa en MySQL

## Instalación y Ejecución
1. Clonar el repositorio(gh repo clone grodriguez-202218/Kinal_App_2022187)
2. Abrir el proyecto en IntelliJ IDEA
3. Compilar el proyecto
4. Ejecutar "KinalAppApplication"
5. Descargar la carpeta de postman para poder acceder a los endpoints especificos.
6. Usar la ruta de (http://localhost:8088/) para poder acceder o entrar a la pagina principal del proyecto.

## Estructura del proyecto
Kinal_App_2022187/
│
├── pom.xml
├── README.md
├── Kinal_App_IN5AM.mwb
├── Kinal_App_2022187.postman_collection.json
│
└── src/
└── main/
├── java/com/gahelrodriguez/kinalapp/
│   │
│   ├── KinalAppApplication.java
│   │
│   ├── config/
│   │   ├── SecurityConfig.java          
│   │   └── WebConfig.java               
│   ├── security/
│   │   └── UsuarioDetailsService.java   
│   │
│   ├── util/
│   │   └── PasswordMigrationUtil.java   
│   │
│   ├── controller/
│   │   ├── ClienteController.java
│   │   ├── ClienteViewController.java
│   │   ├── DetalleVentaController.java
│   │   ├── HomeController.java
│   │   ├── LoginController.java         
│   │   ├── ProductoController.java
│   │   ├── ProductoViewController.java
│   │   ├── UsuarioController.java
│   │   ├── UsuarioViewController.java
│   │   ├── VentaController.java
│   │   └── VentaViewController.java
│   │
│   ├── entity/
│   │   ├── Cliente.java
│   │   ├── DetalleVenta.java
│   │   ├── Producto.java
│   │   ├── Usuario.java
│   │   └── Venta.java
│   │
│   ├── repository/
│   │   ├── ClienteRepository.java
│   │   ├── DetalleVentaRepository.java
│   │   ├── ProductoRepository.java
│   │   ├── UsuarioRepository.java
│   │   └── VentaRepository.java
│   │
│   └── service/
│       ├── IClienteService.java
│       ├── IDetalleVentaService.java
│       ├── IProductoService.java
│       ├── IUsuarioService.java
│       ├── IVentaService.java
│       ├── ClienteService.java
│       ├── DetalleVentaService.java
│       ├── ProductoService.java
│       ├── UsuarioService.java
│       └── VentaService.java
│
└── resources/
├── application.properties
├── static/css/
│   └── styles.css
└── templates/
├── index.html
├── dashboard.html
├── login.html                   
├── layouts/
│   └── layout.html              
├── clientes/
│   ├── lista.html
│   └── formulario.html
├── productos/
│   ├── lista.html
│   └── formulario.html
├── usuarios/
│   ├── lista.html
│   └── formulario.html
└── ventas/
├── lista.html
└── formulario.html