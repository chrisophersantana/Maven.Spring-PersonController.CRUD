package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private PersonRepository repository;


    @Autowired
    public PersonController(PersonRepository repository){this.repository = repository;}



    @PostMapping("/persons")
    public ResponseEntity<Person> create(@RequestBody Person p) {
        return new ResponseEntity<>(repository.save(p), HttpStatus.CREATED);

    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> get(@PathVariable int id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> update(@PathVariable int id, @RequestBody Person person) {
        Person newPerson = repository.findOne(id);
        newPerson.setFirstName(person.getFirstName());
        newPerson.setLastName(person.getLastName());
        return new ResponseEntity<>(repository.save(newPerson), HttpStatus.OK);


    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Person> delete(@PathVariable int id) {
        repository.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> index() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }


}




