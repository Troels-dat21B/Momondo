package com.example.endmondo.controller;

import com.example.endmondo.dto.Age;
import com.example.endmondo.dto.Gender;
import com.example.endmondo.dto.Nationality;
import com.example.endmondo.dto.NameResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class NameController {

    @RequestMapping("/name-info")
    public NameResponse getDetails(@RequestParam String name) {

        //TODO slet det efter "="
        Mono<Age> age; //Lav en getAge(String name)
        Mono<Gender> gender; //Lav en getGender(String name)
        Mono<Nationality> nationality; //Lav en getNationality(String name)

        var resMono = Mono.zip(getAgeForName(name), getGenderForName(name), getNationalityForName(name)).map(t -> {

            NameResponse response = new NameResponse();

            response.setAge(t.getT1().getAge());
            response.setAgeCount(t.getT1().getCount());

            response.setGender(t.getT2().getGender());
            response.setGenderProbability(t.getT2().getProbability());

            response.setCountry(t.getT3().getCountry().get(0).getCountry_id());
            response.setCountryProbability(t.getT3().getCountry().get(0).getProbability());

            return response;


        });

        NameResponse res = resMono.block();
        res.setName(name);
        //Call all setters

        return res;
    }

    Mono<Age> getAgeForName(String name) {
        WebClient client = WebClient.create();
        Mono<Age> age = client.get()
                .uri("https://api.agify.io?name=" + name)
                .retrieve()
                .bodyToMono(Age.class);
        return age;
    }

    Mono<Gender> getGenderForName(String name) {
        WebClient client = WebClient.create();
        Mono<Gender> gender = client.get()
                .uri("https://api.genderize.io?name=" + name)
                .retrieve()
                .bodyToMono(Gender.class);
        return gender;
    }

    Mono<Nationality> getNationalityForName(String name) {
        WebClient client = WebClient.create();
        Mono<Nationality> nationality = client.get()
                .uri("https://api.nationalize.io?name=" + name)
                .retrieve()
                .bodyToMono(Nationality.class);
        return nationality;
    }


}
