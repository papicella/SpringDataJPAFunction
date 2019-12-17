package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.PersonRepository;
import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class SpringDataJpaFunctionApplication {

    private PersonRepository personRepository;

    public SpringDataJpaFunctionApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Bean
    public Function<Long, String> findPersonById() {
        return id -> {
            Gson gson = new Gson();
            Optional<Person> person = personRepository.findById(id);
            return person.isPresent() ? gson.toJson(person.get()) : null;
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaFunctionApplication.class, args);
    }

}
