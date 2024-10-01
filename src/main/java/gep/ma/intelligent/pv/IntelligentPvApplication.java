package gep.ma.intelligent.pv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication
@EnableCassandraRepositories
@EnableAsync

public class IntelligentPvApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentPvApplication.class, args);
	}

}
