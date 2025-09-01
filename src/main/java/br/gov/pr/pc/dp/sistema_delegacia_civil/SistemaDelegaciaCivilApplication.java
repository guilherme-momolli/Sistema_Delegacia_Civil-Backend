package br.gov.pr.pc.dp.sistema_delegacia_civil;

import br.gov.pr.pc.dp.sistema_delegacia_civil.configurations.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		FileStorageConfig.class
})
@SpringBootApplication
public class SistemaDelegaciaCivilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDelegaciaCivilApplication.class, args);
	}

}
