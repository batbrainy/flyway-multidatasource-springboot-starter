# Getting Started

This project is a POC of how to use multiple datasources with spring boot java backend project
This project configures the following:
- Datasources
  - Datasource1 - H2 file based db 
  - Datasource2 - H2 file based db 
- Flyway (configured in `db.migration` under `resources`)
  - db1
    - First datasource sqls
  - db2
    - Second datasource sqls
- JPA repositories (TableOneRepository, TableTwoRepository)
  - 2 repositories (1 per entity)
- Entities (TableOne, TableTwo)
  - TableOne -> Only for TableOneRepository -> For Datasource1
  - TableTwo -> Only for TableTwoRepository -> For Datasource2
- JPAConfigs (per datasource)
  - PlatformTransactionManager
  - LocalContainerEntityManagerFactoryBean
- Controller
  - The controller has 3 GET endpoints for easy testing
  - http://localhost:8080/data -> gets the count of rows in each entity from 2 diff datasources
  - http://localhost:8080/insertOne -> Inserts dummy data into TableOne using datasource1
  - http://localhost:8080/insertTwo -> Inserts dummy data into TableTwo using datasource 2

For this setup to work we need to configure the Transaction manager and make one of them primary
Once this is done and all above is setup, we can now autowire repositories and use them in the controller as demonstrated



For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.4/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/3.4.4/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>`
and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove
those overrides.

