package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.entity.PersonRepository;
import net.minidev.json.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@SpringBootApplication
public class SpringDataJpaFunctionApplication {

    private PersonRepository personRepository;

    public SpringDataJpaFunctionApplication(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaFunctionApplication.class, args);
    }

    @Bean
    public Function<Long, String> findPersonById() {
        return id -> {
            Optional<Person> person = personRepository.findById(id);
            Person p = person.get();
            return p.toString();
        };
    }

    @Bean
    public Function<String, String> findPeopleBySurname() {
        return surname -> {
            List<Person> personList = personRepository.findByLastName(surname.toLowerCase());
            String jsonStr = JSONArray.toJSONString(personList);
            return jsonStr;
        };
    }
}
