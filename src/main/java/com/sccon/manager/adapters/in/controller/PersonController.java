package com.sccon.manager.adapters.in.controller;

import com.sccon.manager.adapters.in.controller.mapper.PersonMapper;
import com.sccon.manager.adapters.in.controller.request.PersonRequest;
import com.sccon.manager.adapters.in.controller.response.PersonResponse;
import com.sccon.manager.application.core.domain.Person;
import com.sccon.manager.application.core.ports.in.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private InsertPersonInputPort insertPersonInputPort;
    @Autowired
    private FindPersonInputPort findPersonInputPort;
    @Autowired
    private UpdatePersonInputPort updatePersonInputPort;
    @Autowired
    private DeletePersonInputPort deletePersonInputPort;
    @Autowired
    private CalculatePersonInputPort calculatePersonInputPort;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonMapper personMapper;
    private final LocalDate currentDate = LocalDate.of(2023, 2, 7); // Data fixa conforme documento

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        var personList = findPersonInputPort.findAll();
        return ResponseEntity.ok(personList);
    }

    @PostMapping
    public ResponseEntity<Void> insertPerson(@RequestBody PersonRequest personRequest) {
        var person = personMapper.toPerson(personRequest);
        if (personRequest.getId() != null && insertPersonInputPort.isIdAvailable(personRequest.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Já existe uma pessoa com o ID " + personRequest.getId());
        }
        insertPersonInputPort.insert(person);

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable final Long id) {
        var person = findPersonInputPort.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Pessoa com id " + id + " não encontrada."));
        var personResponse = modelMapper.map(person, PersonResponse.class);
        return ResponseEntity.ok().body(personResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable final Long id,
                                             @RequestBody PersonRequest personRequest) {
        var person = modelMapper.map(personRequest, Person.class);
        if (!insertPersonInputPort.isIdAvailable(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa com id " + id + " não encontrada.");
        }
        updatePersonInputPort.update(id, person);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdatePerson(@PathVariable final Long id,
                                                    @RequestBody PersonRequest personRequest) {
        var person = modelMapper.map(personRequest, Person.class);
        if (!insertPersonInputPort.isIdAvailable(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa com id " + id + " não encontrada.");
        }
        updatePersonInputPort.updatePartial(id, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable final Long id) {
        if (!insertPersonInputPort.isIdAvailable(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa com id " + id + " não encontrada.");
        }
        deletePersonInputPort.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/age")
    public ResponseEntity<Long> getAge(@PathVariable Long id, @RequestParam String output) {
        if (!insertPersonInputPort.isIdAvailable(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa com id " + id + " não encontrada.");
        }
        var age = calculatePersonInputPort.calculateAge(id, output, currentDate);
        return ResponseEntity.ok().body(age);
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<BigDecimal> getSalary(@PathVariable Long id, @RequestParam String output) {
        if (!insertPersonInputPort.isIdAvailable(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa com id " + id + " não encontrada.");
        }
        var salary = calculatePersonInputPort.calculateSalary(id, output, currentDate);
        return ResponseEntity.ok(salary);
    }
}
