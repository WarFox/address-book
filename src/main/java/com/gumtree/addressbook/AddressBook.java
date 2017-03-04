package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;

import java.util.ArrayList;
import java.util.List;
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

}
