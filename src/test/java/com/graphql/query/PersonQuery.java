package com.graphql.query;

import com.google.common.collect.Lists;
import com.graphql.Person;
import com.graphql.dao.PersonDao;

import java.util.List;

public class PersonQuery implements Query {

    private PersonDao personDao;

    public  PersonQuery(){
        personDao=new PersonDao();
    }


    public Person getById(Integer  id){
        return personDao.getById(id);
    }


    public List<Person> gePersons(Integer id) {
        return Lists.newArrayList(personDao.getById(1));
    }
}
