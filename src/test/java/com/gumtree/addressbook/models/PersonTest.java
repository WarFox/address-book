package com.gumtree.addressbook.models;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    public void getNameReturnsName() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getName()).isEqualTo("name");
    }

    @Test
    public void getGenderReturnsGender() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getGender()).isEqualTo(Gender.Female);
    }

    @Test
    public void getDobReturnsDob() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getDob()).isEqualTo(LocalDate.now());
    }

}
