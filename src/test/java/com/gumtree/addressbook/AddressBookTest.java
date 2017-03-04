package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressBookTest {

    private List<Person> contacts = new ArrayList<>();
    Person mike = new Person("Mike", Gender.Male, LocalDate.of(1985, 1, 1));

    @Before
    public void setUp() throws Exception {
        contacts.add(mike);
        contacts.add(new Person("Clara", Gender.Female, LocalDate.of(1989, 1, 1)));
        contacts.add(new Person("Bill", Gender.Male, LocalDate.of(1982, 1, 1)));
        contacts.add(new Person("Jack", Gender.Male, LocalDate.of(1983, 1, 1)));
        contacts.add(new Person("Daisy", Gender.Female, LocalDate.of(1987, 1, 1)));
    }

    @Test
    public void testFindMales() throws Exception {
        AddressBook addressBook = new AddressBook(contacts);
        List<Person> contacts = addressBook.findByGender(Gender.Male);
        assertThat(contacts).hasSize(3);
        contacts.forEach(contact -> assertThat(contact.getGender()).isEqualTo(Gender.Male));
    }

    @Test
    public void testFindFemales() throws Exception {
        AddressBook addressBook = new AddressBook(contacts);
        List<Person> contacts = addressBook.findByGender(Gender.Female);
        assertThat(contacts).hasSize(2);
        contacts.forEach(contact -> assertThat(contact.getGender()).isEqualTo(Gender.Female));
    }

    @Test
    public void testEmptyContactsShouldReturnEmptyList() throws Exception {
        AddressBook addressBook = new AddressBook(new ArrayList<>());
        assertThat(addressBook.findByGender(Gender.Female)).isEmpty();
        assertThat(addressBook.findByGender(Gender.Male)).isEmpty();
    }

    @Test
    public void testSortByDob() throws Exception {
        AddressBook addressBook = new AddressBook(contacts);
        List<Person> sortedContacts = addressBook.sortByDob();
        Person oldestPerson = sortedContacts.stream().findFirst().get();
        assertThat(oldestPerson.getName()).isEqualTo("Bill");
    }

    @Test
    public void testFindByFirstName() throws Exception {
        AddressBook addressBook = new AddressBook(contacts);
        Optional<Person> personOptional = addressBook.findByFirstName("Mike");
        assertThat(personOptional.isPresent()).isTrue();
        personOptional.map(person -> {
            assertThat(person.getDob()).isEqualTo(this.mike.getDob());
            assertThat(person.getGender()).isEqualTo(Gender.Male);
            assertThat(person.getName()).isEqualTo("Mike");
            return person;
        });
    }

    @Test
    public void testFindByFirstNameThatDoesNotExist() throws Exception {
        AddressBook addressBook = new AddressBook(contacts);
        assertThat(addressBook.findByFirstName("DoesNotExist").isPresent()).isFalse();
    }

}
