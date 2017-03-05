package com.gumtree.addressbook.models;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    @Test
    public void testGetNameReturnsName() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getName()).isEqualTo("name");
    }

    @Test
    public void testGetGenderReturnsGender() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getGender()).isEqualTo(Gender.Female);
    }

    @Test
    public void testGetDobReturnsDob() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.getDob()).isEqualTo(LocalDate.now());
    }

    @Test
    public void testToString() {
        Person person = new Person("name", Gender.Female, LocalDate.now());
        assertThat(person.toString()).isEqualTo("Person{name='name', gender=Female, dob=2017-03-05}");
    }

}
