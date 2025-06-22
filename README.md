
# Foto Kit

(It's under development process)

This is Spring Boot demo app. 
It demonstrates data manipulation, CRUD operations.
The app is assumed to be run on a standalone Tomcat server.

### Techstack

* [Spring Framework.](https://spring.io/)
* [Spring Web MVC.](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
* [Hibernate.](https://hibernate.org/)
* [MySQL.](https://www.mysql.com/)
* [Bootstrap 5.](https://getbootstrap.com/)
* [Thymeleaf.](https://www.thymeleaf.org/)
* [Lombok.](https://projectlombok.org/)
* [jQuery.](https://jquery.com/)

### Database

SQL-query for DB table(s)

```sql
CREATE TABLE IF NOT EXISTS fotos
( id BIGINT NOT NULL AUTO_INCREMENT,
  img VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
```

### Configuration

Start [Spring Initializr.](https://start.spring.io/) 
Configure the project (the numeric designations of the framework versions may
change as the framework develops).

In **Project** choose `Maven`. In **Language** choose `Java`. 
In **Spring Boot** choose latest stable Spring Boot version.

**Project Metadata**:
- `Group`: com.example
- `Artifact`: Spring-Boot-Foto-Kit
- `Name`: Foto-Kit
- `Description`: Demo project for Spring Boot
- `Packaging name`: com.example.Spring-Boot-Foto-Kit
- `Packaging`: War
- `Java`: 21 (i.o. choose latest LTS version)

**Dependencies** (choose necessary dependencies):
- Spring Boot DevTools
- Lombok
- Spring Web
- Thymeleaf
- Spring Data JPA
- MySQL Driver

Check your configurations. Click `GENERATE`. A ZIP-file should be downloaded. 
Unzip the generated ZIP-file, move extracted content to your projects' folder.

Open the project in IDE (e.g. IntelliJ IDEA). Research its files and folders.

The `spring-boot-starter-web` contains the Tomcat by default.
**In order to run** your Spring Boot app on a standalone Tomcat server,
you can comment this Tomcat section in `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```
or exclude Tomcat

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
    </exclusions>
</dependency>
```

Also, `FotoKitApplication` class should be modified to extend 
`SpringBootServletInitializer` class.

After that your Spring Boot Application WAR can be run on an external 
Tomcat server.

**Files that are uploaded** to the server are dynamic content, 
so it is not advisable to store them in the `static` folder of the project.

Class `./config/ExtraResourceWebConfiguration` is a configuration class 
for configuring the ability of a compiled Spring application
to see resources outside the source folder, i.e. extra resources.
In this demo, the `uploads` directory is external to the resources 
in the source folder. Files will later be uploaded to the `uploads` 
directory.

For proper application configuration **also modify `application.properties` 
file**. In remote repository it is `application.example.properties` file, 
and `application.properties` file is ignored by Git by mentioning it in 
`.gitignore` file.

