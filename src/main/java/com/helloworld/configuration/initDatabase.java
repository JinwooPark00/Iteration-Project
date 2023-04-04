package com.helloworld.configuration;

import com.helloworld.DAO.CustomerRepository;
import com.helloworld.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Customer("John", "Doe")));
            log.info("Preloading " + repository.save(new Customer("Jane", "Doess" )));
        };
    }
}