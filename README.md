# Estudiante Programación

API REST desarrollada con Spring Boot para la gestión de cursos y temas.

Permite:
- CRUD de cursos
- CRUD de temas
- Asociar temas a cursos
- Buscar cursos por palabra clave

---

## Tecnologías

- Java
- Spring Boot
- MySQL
- Postman

---

## Cómo ejecutar el proyecto

**1. Clonar repositorio:**
```bash
git clone https://github.com/tu-usuario/estudianteProgramacion.git
cd estudianteProgramacion
```

**2. Crear la base de datos:**
En MySQL ejecutar: `CREATE DATABASE estudiante_programacion;`

**3. Crear la estructura de la base de datos:**
Ejecutar el archivo: `/database/schema.sql`

**4. Configurar credenciales:**
Crear un archivo en `src/main/resources/application-local.properties`
Con el siguiente contenido:
  ```bash
  spring.datasource.username=TU_USUARIO
  spring.datasource.password=TU_PASSWORD
  ```

**5. Ejecutar la aplicación:** `./mvnw spring-boot:run`
La API estará disponible en: `http://localhost:8080`

---

### Base de datos
El script de la base de datos se encuentra en: `/database/schema.sql`

---

### Pruebas con Postman

1. Importar colección
`/postman/EstudianteProgramacion.postman_collection.json`

2. Importar environment
`/postman/local_environment.json`

3. Configurar variable
`base_url = http://localhost:8080`
