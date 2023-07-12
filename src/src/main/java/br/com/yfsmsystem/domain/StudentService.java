package br.com.yfsmsystem.domain;

import java.time.LocalDate;

public class StudentService {

    public Boolean saveNewStudent(Student student) {
        return student != null;
    }


    public String welcomeStudent(Student student) {
        return "Welcome: " + student.getName();
    }

    public Boolean verifyIsBirthday(Student student){
        return  LocalDate.now().equals(student.getBirthday());
    }

    public Boolean verifyStatusByAge(Student student){
        return student.getStatus().equals("OLD");
    }
}
