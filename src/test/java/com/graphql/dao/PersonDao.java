package com.graphql.dao;

import com.graphql.Person;

public class PersonDao {

    public Person getById(Integer id){
        Person person = new Person();
        person.setId(1);
        person.setName("lise");
        return person;
    }
}
