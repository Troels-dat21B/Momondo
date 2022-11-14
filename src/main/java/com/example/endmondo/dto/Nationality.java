package com.example.endmondo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Setter
@Getter
public class Nationality {
    public ArrayList<Country> country;
    public String name;
}
