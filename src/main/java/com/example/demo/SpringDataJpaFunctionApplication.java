package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.PersonRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class SpringDataJpaFunctionApplication {

    private PersonRepository personRepository;

    public SpringDataJpaFunctionApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Bean
    public Function<Long, String> findPersonById() {
        return id -> {
            log.info("Attempting to find Person with id : " + id);
            Gson gson = new Gson();
            Optional<Person> person = personRepository.findById(id);
            return gson.toJson(person.orElse(null));
        };
    }

    @Bean
    public Function<String, String> findByLastName() {
        return surname -> {
            log.info("Attempting to find People with surname : " + surname);
            Gson gson = new Gson();
            List<Person> personList = personRepository.findByLastName(surname.toLowerCase());
            return gson.toJson(personList);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaFunctionApplication.class, args);
    }

}
