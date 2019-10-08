# SPRING BASICS COLLECTION

This is a collection of Spring-based Java projects using Maven. It's meant as an educational showcase of the different combinations of technologies we can find within the larger Spring ecosystem.

The functionality of these projects is basic by nature. My intention when creating them was to create fully working examples of these technologies that, unlike most examples found online, wasn't so simple as to be unusable for someone trying to build their own Spring project for the first time.

All programs are different implementation of the same idea. They pretend to be HR management tools, with simple CRUD utilities.

To make fun for myself I made it be the HR tool at CTU, as shown in the TV series [24]( https://en.wikipedia.org/wiki/24_(TV_series) ).


## Projects

They combine different qualities:
- Initiation methods: Spring, Spring Boot
- User interfaces: CLI, GUI
- Database-solution implementations: JDBC, JPA

The collection contains the following projects:
- `spring-cli-jdbc`: CLI java application, using JDBC DAOs, on Spring (non-boot)
- `spring-cli-jpa`: CLI java application, using JPA repos, on Spring (non-boot)
- `spring-gui-jdbc`: GUI java application, using JDBC DAOs, on Spring (non-boot)
- `spring-gui-jpa`: GUI java application, using JPA repos, on Spring (non-boot)
- `boot-cli-jpa`: CLI java application, using JPA repos, on Spring Boot
- `boot-gui-jpa`: GUI java application, using JPA repos, on Spring Boot


All databases use file-based H2 backends.

All GUI apps use Swing.


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
