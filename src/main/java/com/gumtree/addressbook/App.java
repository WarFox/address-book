package com.gumtree.addressbook;

import com.gumtree.addressbook.models.Gender;
import com.gumtree.addressbook.models.Person;
import com.gumtree.addressbook.utils.DateUtils;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads Address book from standard input
 */
public class App {

    public static final Config conf = ConfigFactory.load();
    public static final String dateFormat = conf.getString("date.format");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Person> contacts = new ArrayList<>();

        while (scan.hasNext()) {
            String line = scan.nextLine();
            contacts.add(createPerson(line));
        }

        AddressBook addressBook = new AddressBook(contacts);
        questionAnswer("How many males are in the address book?", addressBook.filterByGender(Gender.Male).size());
        questionAnswer("Who is the oldest person in the address book?", addressBook.sortByDob().stream().findFirst().get().getName());
        Person bill = addressBook.findByFirstName("Bill").get();
        Person paul = addressBook.findByFirstName("Paul").get();
        questionAnswer("How many days older is Bill than Paul?", DateUtils.daysBetween(bill.getDob(), paul.getDob()));
    }

    public static void questionAnswer(String question, Object object) {
        System.out.println("Question: " + question);
        System.out.println("Answer: " + object);
    }

    public static Person createPerson(String line) {
        String[] split = line.split(",");
        return createPerson(split[0], split[1], split[2]);
    }

    public static Person createPerson(String name, String genderString, String dobString) {
        Gender gender = Gender.valueOf(genderString.trim());
        LocalDate dob = DateUtils.parseDate(dobString.trim());
        return new Person(name, gender, dob);
    }

}
