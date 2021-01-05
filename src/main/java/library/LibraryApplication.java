package library;

import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LibraryApplication.class, args);
	}

}
