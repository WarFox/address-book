package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    public void testCreatePersonFromString() throws Exception {
        Person person = App.createPerson("firstname lastname, Male, 12/12/12");
        assertThat(person.getName()).isEqualTo("firstname lastname");
        assertThat(person.getDob()).isEqualTo(LocalDate.of(2012, 12, 12));
        assertThat(person.getGender()).isEqualTo(Gender.Male);
    }

    @Test
    public void testCreatePersonFrom3Strings() throws Exception {
        Person person = App.createPerson("firstname lastname", "Male", "12/12/12");
        assertThat(person.getName()).isEqualTo("firstname lastname");
        assertThat(person.getDob()).isEqualTo(LocalDate.of(2012, 12, 12));
        assertThat(person.getGender()).isEqualTo(Gender.Male);
    }

}
