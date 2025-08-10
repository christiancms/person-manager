package com.sccon.manager.adapters.out;

import com.sccon.manager.adapters.out.repository.PersonRepository;
import com.sccon.manager.application.core.domain.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class InMemoryPersonRepository implements PersonRepository {

    private final Map<Long, Person> personMap = new HashMap<>();
    private static final String PERSON_ID = "Pessoa com ID ";

    @PostConstruct
    public void init() {
        personMap.put(1L, new Person(1L, "Huguinho", LocalDate.of(2005, 5, 15), LocalDate.of(2020, 1, 10)));
        personMap.put(2L, new Person(2L, "Zézinho", LocalDate.of(1995, 3, 22), LocalDate.of(2018, 6, 5)));
        personMap.put(3L, new Person(3L, "Luizinho", LocalDate.of(2000, 5, 20), LocalDate.of(2022, 2, 20)));
    }

    @Override
    public List<Person> findAll() {
        return personMap.values().stream().sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(personMap.get(id));
    }

    @Override
    public void save(Person person) {
        if (person.getId() == null) {
            // Gera um novo ID se não fornecido (simulando auto-incremento)
            long newId = personMap.keySet().stream().max(Long::compareTo).orElse(0L) + 1;
            person.setId(newId);
        }
        personMap.put(person.getId(), person);
    }

    @Override
    public void delete(Long id) {
        if (!personMap.containsKey(id)) {
            throw new RuntimeException(PERSON_ID + id + " não encontrada para exclusão.");
        }
        personMap.remove(id);
    }

    @Override
    public void update(Long id, Person personUpdate) {
        if (!personMap.containsKey(id)) {
            throw new RuntimeException(PERSON_ID + personUpdate.getId() + " não encontrada para atualização.");
        }
        personUpdate.setId(id);
        personMap.put(id, personUpdate);
    }

    @Override
    public void updatePartial(Long id, Person personUpdate) {
        if (!personMap.containsKey(id)) {
            throw new RuntimeException(PERSON_ID + personUpdate.getId() + " não encontrada para atualização.");
        }
        var currentPerson = personMap.get(id);
        if (currentPerson != null) {
            if (personUpdate.getName() != null) {
                currentPerson.setName(personUpdate.getName());
            }
            if (personUpdate.getBirth() != null) {
                currentPerson.setBirth(personUpdate.getBirth());
            }
            if (personUpdate.getAdmission() != null) {
                currentPerson.setAdmission(personUpdate.getAdmission());
            }
        }
    }

    @Override
    public Boolean isIdAvailable(Long id) {
        return personMap.containsKey(id);
    }
}