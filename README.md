
# Foto Kit

This is Spring Boot demo app. It demonstrates data manipulation, CRUD operations, 
file uploading to external server. The app is assumed to be run on a standalone 
Apache Tomcat (Tomcat) server. This demo app is only for demonstration
and/or educational purposes.

**Table of Contents**

* [Tech Stack](#tech-stack)
* [Database](#database)
* [Configuration](#configuration)
* [Deployment](#deployment)
* [Manual testing](#manual-testing)
* [Resources](#manual-testing)

### Tech Stack

* [Spring Framework.](https://spring.io/)
* [Spring Web MVC.](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
* [Hibernate.](https://hibernate.org/)
* [MySQL.](https://www.mysql.com/)
* [Bootstrap 5.](https://getbootstrap.com/)
* [Thymeleaf.](https://www.thymeleaf.org/)
* [Lombok.](https://projectlombok.org/)
* [jQuery.](https://jquery.com/)


### Database

This demo app uses MySQL RDBMS. SQL-query for DB table

```sql
CREATE TABLE IF NOT EXISTS fotos
( id BIGINT NOT NULL AUTO_INCREMENT,
  img VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);
```
Database server should run together with standalone Tomcat server, 
in order to manually test the app.


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

The `spring-boot-starter-web` contains the **Tomcat by default**. 
According to Spring Boot documentation, `<scope>provided</scope>` here

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```
ensures that the default Tomcat does not interfere with external Tomcat 
to which a WAR file of your application will be deployed.

**Also**, `FotoKitApplication` class should be modified to extend 
`SpringBootServletInitializer` class.

**Files that are uploaded** to the server are dynamic content, 
so it is not advisable to store them in the `static` folder of the project.

Class `./config/ExtraResourceWebConfiguration` is a configuration class 
for configuring the ability of a compiled Spring application
to see resources outside the source folder, i.e. extra resources.

In this demo app, **the `uploads` directory is external** to the resources 
in the source folder. Files will later be uploaded to the `uploads` 
directory (in a case of running the app through IDE). 
For standalone Tomcat (in a case when WAR file should be deployed on 
external server), it's better make static `uploads` directory 
in Tomcat `./bin` folder.

For proper application configuration **also modify `application.properties` 
file**. In this remote Git repository it is `application.example.properties` file, 
and `application.properties` file is ignored by Git by mentioning it in 
`.gitignore` file.


### Deployment

To remove unnecessary generated build code (if it exists), run

```bash
$ mvn clean
```

To generate WAR file, run

```bash
$ mvn install
```

Also, these Maven commands can be run through IDE.

External/standalone Tomcat should be running.
Starting Tomcat: `startup.bat` - for Windows, `startup.sh` - for MacOS/Linux.
Stopping Tomcat: `shutdown.bat` - for Windows, `shutdown.sh` - for MacOS/Linux.

Copy `Spring-Boot-Foto-Kit-0.0.1-SNAPSHOT.war` file (it appears 
in project `target` folder) into Tomcat `./webapps` directory. 
This file should be unzipped automatically by Tomcat.


### Manual testing

Database server and Tomcat server must running.

This demo app has two types of users, namely - user, and admin.

Assuming, Tomcat running on port 8080. For user type, in web browser, start 
```text
http://localhost:8080/Spring-Boot-Foto-Kit-0.0.1-SNAPSHOT/
```
For admin type, in web browser, start
```text
http://localhost:8080/Spring-Boot-Foto-Kit-0.0.1-SNAPSHOT/admin/fotos
```
After that, you can manually test the app functionality.


### Resources

* https://spring.io/guides/gs/uploading-files
* https://www.baeldung.com/sprint-boot-multipart-requests
* https://www.thymeleaf.org/doc/articles/springmvcaccessdata.html
* https://www.baeldung.com/thymeleaf-in-spring-mvc
* https://docs.spring.io/spring-boot/how-to/deployment/index.html
* https://www.baeldung.com/spring-boot-war-tomcat-deploy

