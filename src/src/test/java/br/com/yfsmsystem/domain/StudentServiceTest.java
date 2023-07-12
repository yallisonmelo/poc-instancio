package br.com.yfsmsystem.domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.instancio.Select.all;
import static org.instancio.Select.field;

class StudentServiceTest {
    StudentService studentService = new StudentService();

    @Test
    void testSaveNewStudent() {
        var student = Instancio.create(Student.class);
        var result = studentService.saveNewStudent(student);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testWelcomeStudent() {
        var student = Instancio.of(Student.class).set(field(Student::getName), "Yallison").create();
        var result = studentService.welcomeStudent(student);
        Assertions.assertEquals("Welcome: " + student.getName(), result);
    }

    @Test
    void testVerifyIsBirthday() {
        var student = Instancio.of(Student.class)
                .supply(all(LocalDate.class), () -> LocalDate.now())
                .create();
        var resutl = studentService.verifyIsBirthday(student);
        Assertions.assertEquals(Boolean.TRUE, resutl);
    }

    @Test
    void testVerifyStatusByAge(){
        var student = Instancio.of(Student.class)
                .set(field(Student::getAge), 19)
                .onComplete(all(Student.class), (Student s) ->
                        s.setStatus(s.getAge() < 18 ? "NEW" : "OLD")).create();

        var result = studentService.verifyStatusByAge(student);

        Assertions.assertEquals(Boolean.TRUE,result);
    }

    @Test
    void testVerifyStatusByAgeUsingGenerate(){
        var student = Instancio.of(Student.class)
                //aleatory localdate
                .generate(field(Student::getBirthday), gen -> gen.temporal().localDate().past())
                .onComplete(all(Student.class), (Student s) ->
                        s.setStatus(s.getAge() < 18 ? "NEW" : "OLD")).create();

        var result = studentService.verifyStatusByAge(student);

        Assertions.assertEquals(Boolean.TRUE,result);
    }

}
