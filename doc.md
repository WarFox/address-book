## Setup

```
mvn archetype:generate -DgroupId=com.gumtree.addressbook -DartifactId=address-book -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

## Assumptions
- No two people have same dob
- Read from standard input

## Run
```
mvn package; cat AddressBook | java -jar target/address-book-1.0-SNAPSHOT.jar
```

