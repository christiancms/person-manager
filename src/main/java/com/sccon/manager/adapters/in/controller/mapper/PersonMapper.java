package com.sccon.manager.adapters.in.controller.mapper;

import com.sccon.manager.adapters.in.controller.request.PersonRequest;
import com.sccon.manager.application.core.domain.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;

    public PersonMapper(ModelMapper modelMapper) {
        this.modelMapper = new ModelMapper();

        this.modelMapper.addMappings(new PropertyMap<PersonRequest, Person>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public Person toPerson(PersonRequest personRequest) {
        return modelMapper.map(personRequest, Person.class);
    }
}
