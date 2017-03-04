package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressBook {

    private List<Person> contacts = new ArrayList<>();

    public AddressBook(List<Person> contacts) {
        this.contacts = contacts;
    }

    public List<Person> findByGender(Gender gender) {
        return contacts.stream()
                .filter(person -> gender.equals(person.getGender()))
                .collect(Collectors.toList());
    }

    public List<Person> sortByDob() {
        return contacts.stream()
                .sorted((p1, p2) -> p1.getDob().compareTo(p2.getDob()))
                .collect(Collectors.toList());
    }

    public Optional<Person> findByFirstName(String firstName) {
        return contacts.stream()
                .filter(person -> person.getName().startsWith(firstName))
                .findFirst();
    }

}
