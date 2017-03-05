package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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

    @Test
    public void testCreatePersonThrowsExceptionIfLessThan2Commas() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Each line should have exactly 3 sections separated by comma");
        App.createPerson("firstname lastname");
    }

    @Test
    public void testCreatePersonThrowsExceptionIfGreaterThan2Commas() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Each line should have exactly 3 sections separated by comma");
        App.createPerson("firstname lastname, a, b, c");
    }

    @Test
    public void testCreatePersonThrowsExceptionIfDateCannotBeParsed() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Failed to create person from input");
        App.createPerson("firstname lastname, Male, 45/34/90");
    }

    @Test
    public void testCreatePersonThrowsExceptionIfDateCannotBeParsedByDefaultFormat() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Failed to create person from input");
        App.createPerson("firstname lastname, Male, 12-12-12");
    }

    @Test
    public void testCreatePersonThrowsExceptionIfGenderCannotBeParsed() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Failed to create person from input");
        App.createPerson("firstname lastname, Abcd, 12/12/12");
    }

    @Test
    public void testReadContacts() throws Exception {
        String data = "Mike, Male, 17/12/89\r\n";
        data += "Natasha, Female, 17/12/89\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        Scanner scanner = new Scanner(System.in);

        List<Person> persons = App.readContacts(scanner);
        assertThat(persons).hasSize(2);
    }

    @Test
    public void testAnswersMethodWithSpy() throws Exception {
        AddressBook addressBook = new AddressBook(new ArrayList<>());
        AddressBook spy = Mockito.spy(addressBook);
        Person mockPerson = new Person("Name", Gender.Male, LocalDate.now());
        List<Person> people = new ArrayList<>();
        people.add(mockPerson);
        Mockito.doReturn(people).when(spy).sortByDob();
        Mockito.doReturn(Optional.of(mockPerson)).when(spy).findByFirstName("Bill");
        Mockito.doReturn(Optional.of(mockPerson)).when(spy).findByFirstName("Paul");

        App.answers(spy);

        Mockito.verify(spy).filterByGender(Gender.Male);
        Mockito.verify(spy).sortByDob();
        Mockito.verify(spy).findByFirstName("Bill");
        Mockito.verify(spy).findByFirstName("Paul");
    }

}
