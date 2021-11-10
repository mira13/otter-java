package ebi.ensembl.otter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Otter java server is an evolution of perl-based otter server, it is
 * independent of ensembl perl-api
 * 
 * We prefer to use native or JPQL queries for complex requests, it give us
 * performance advantage, and more flexibility for optimisation.
 * 
 * This server is compatible with perl otter client at JavaClient branch
 * 
 * @author Mira
 *
 */
@SpringBootApplication
public class OtterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtterApplication.class, args);
	}
}
