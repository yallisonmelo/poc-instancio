package br.com.yfsmsystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Student {

    private String name;
    private String email;
    private int age;
    private String status;
    private String city;
    private LocalDate birthday;
}
