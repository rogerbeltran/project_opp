
package com.ups.oop.service;

import com.ups.oop.dto.Person;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private List<PersonDTO> personList = new ArrayList<>();
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public ResponseEntity createPerson(PersonDTO person) {
        String personId = person.getId();
        boolean wasFound = findPerson(personId);
        if(wasFound){
            String errorMessage = "Person with id " + personId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Person with id " + person.getId() + " already exists");
        } else {
            personList.add(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }

    private boolean findPerson(String id){
        for(PersonDTO person: personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                return true;
            }
        }
        return false;
    }


    public ResponseEntity getAllPeople() {
        List<PersonDTO> peopleList = getPeople();if(peopleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public List<PersonDTO> getPeople(){
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();
        for (Person p : personIterable) {
            PersonDTO person = new PersonDTO(p.getPersonId(), p.getName() + " " + p.getLastName(), p.getAge());
            peopleList.add(person);
        }
        return peopleList;
    }


    public ResponseEntity getPersonById(String personid) {

        // Optional<Person> personOptional = personRepository.findById(Long.valueOf(id));
        Optional<Person> personOptional = personRepository.findByPersonId(personid);
        if(personOptional.isPresent()){
            //if record was found
            Person personFound = personOptional.get();
            PersonDTO person = new PersonDTO(personFound.getPersonId(),
                    personFound.getName() + "-" + personFound.getLastName(), personFound.getAge());
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } else {
            //if record wasn't found
            String errorMessage = "person with id " + personid + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }



    public PersonDTO updatePerson(String id, PersonDTO person){
        PersonDTO per = new PersonDTO();
        int index = 0;
        for(PersonDTO pers : personList){
            if(id.equalsIgnoreCase(pers.getId())){
                personList.set(index, person);
                return person;
            }
            index++;
        }
        return per;
    }

    private int findIndex(String id){
        int indexFound = -1;
        int index = 0;
        for(PersonDTO p : personList){
            if(id.equalsIgnoreCase(p.getId())){
                return index;
            }
            index++;
        }
        return -1;
    }

    public String deletePersonById(String id) {
        String message = "Person with id" + id;
        for (PersonDTO per : personList) {
            if (id.equalsIgnoreCase(per.getId())) {
                personList.remove(per);
                return message + " Removed successfully";
            }
        }
        return message + " not found";
    }
}
