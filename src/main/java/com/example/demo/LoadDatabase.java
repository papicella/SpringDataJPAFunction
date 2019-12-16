package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDB (PersonRepository personRepository) {
        return args -> {
            log.info("Loading : " + personRepository.save(new Person("pas","apicella")));
            log.info("Loading : " + personRepository.save(new Person("lucia","apicella")));
            log.info("Loading : " + personRepository.save(new Person("lucas","apicella")));
            log.info("Loading : " + personRepository.save(new Person("siena","apicella")));
        };
    }
}
