package com.example.endmondo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NameResponse {
    String name;
    String gender;
    double genderProbability;
    int age;
    int ageCount;
    String country;
    double countryProbability;


    public void setGenderProbability(double probability) {
        genderProbability = probability * 100;
    }

    public void setCountryProbability(double probability) {
        countryProbability = probability * 100;
    }
}
