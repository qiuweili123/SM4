package com.graphql;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import graphql.GraphQL;
import graphql.schema.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * @author yemingfeng
 */
public class MainClass {

    private static List<Person> persons;

    public static void main(String[] args) throws IOException {
        init();
        GraphQLObjectType.Builder dogBuilder = newObject()


                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLInt))

                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("age")
                        .type(GraphQLInt));

        GraphQLObjectType dogType = dogBuilder.name("dog").build();


        GraphQLObjectType personType = newObject()
                .name("person")

                .field(newFieldDefinition()
                        .name("id")
                        .type(GraphQLInt))

                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("password")
                        .type(GraphQLString))

                .field(newFieldDefinition()
                        .name("dogs")
                        .type(new GraphQLList(dogType)))
                .build();

        GraphQLObjectType dogExtType = dogBuilder.name("dogExt")
                .field(newFieldDefinition()
                        .name("person")
                        .type(personType))
                .build();


        GraphQLFieldDefinition personDefinition =
                GraphQLFieldDefinition.newFieldDefinition()
                        .name("person")
                        .type(personType)
                       .argument(GraphQLArgument.newArgument().name("id").type(GraphQLInt))
                       .argument(GraphQLArgument.newArgument().name("name").type(GraphQLString))
                        //对象作为参数不支持
                        //.argument(GraphQLArgument.newArgument().name("person").type(   ))
                        .dataFetcher(e -> {
                            Integer id = e.getArgument("id");
                            String name = e.getArgument("name");
                            System.out.println("##id==" + id + "##name::" + name);

                            for (Person person : persons) {
                                /*if (person.getId() == id) {
                                    System.out.println("query person");
                                    return person;
                                }*/
                                if (person.getName().equals(name)) {
                                    System.out.println("person::" + person);
                                    return person;
                                }
                            }
                            return null;
                        })
                        /* .dataFetcher(new DataFetcher() {
                             public Object get(DataFetchingEnvironment dataFetchingEnvironment) {
                                 int id = dataFetchingEnvironment.getArgument("id");
                                 String name
                                 for (Person person : persons) {
                                     if (person.getId() == id) {
                                         System.out.println("query person");
                                         return person;
                                     }
                                 }
                                 return null;
                             }
                         })*/
                        .build();

        GraphQLFieldDefinition dogDefinition =
                GraphQLFieldDefinition.newFieldDefinition()
                        .name("dogExt")
                        .type(dogExtType)
                        .argument(GraphQLArgument.newArgument().name("id").type(GraphQLInt))
                       // .argument(GraphQLArgument.newArgument().name("name").type(GraphQLString))
                        .dataFetcher(e -> {
                            Integer id = e.getArgument("id");
                            String name = e.getArgument("name");

                            for (Person person : persons) {
                                List<Dog> dogs = person.getDogs();
                                for (Dog dog : dogs) {
                                    if (dog.getId() == id) {
                                        dog.setPerson(person);
                                        return dog;
                                    }
                                }
                            }
                            return null;
                        })
                        .build();


        GraphQLSchema pesonSchema = GraphQLSchema.newSchema()
                .query(newObject()
                        .name("personQuery")
                        .field(personDefinition)
                        .build())
                .build();

        GraphQL personGraphQL = GraphQL.newGraphQL(pesonSchema).build();
//一对多的查询
        System.out.println("" + personGraphQL.execute("{person(id:1){id,name,dogs{id}}}").getData());
        System.out.println("" + personGraphQL.execute("{person(id:2){id,name,dogs{id,name,age}}}").getData());
        System.out.println("" + personGraphQL.execute("{person(id:3){id,name,dogs{id,name,age}}}").getData());
        System.out.println("" + personGraphQL.execute("{person(id:1,name:\"Aiden\"){id,name,dogs{id,name,age}}}").getData());
        System.out.println("" + personGraphQL.execute("{person(name:\"Aiden\"){id,name,dogs{id,name,age}}}").getData());

        System.out.println("-------------split----------");
        //从子对象开始多对一的查询
        GraphQLSchema dogSchema = GraphQLSchema.newSchema()
                .query(newObject()
                        .name("dogQuery")
                        .field(dogDefinition)
                        .build())
                .build();
        GraphQL dogGraphQL = GraphQL.newGraphQL(dogSchema).build();

        System.out.println("" + dogGraphQL.execute("{dogExt(id:1){id,name,person{id,name}}}").getData());
    }

    private static void init() throws IOException {
        InputStream inputStream = MainClass.class.getClassLoader().getResourceAsStream("person.json");
        byte[] bytes = new byte[1024];
        int length;
        StringBuilder stringBuilder = new StringBuilder();
        while ((length = inputStream.read(bytes)) != -1) {
            stringBuilder.append(new String(bytes, 0, length));
        }

        persons = new Gson()
                .fromJson(stringBuilder.toString(),
                        new TypeToken<ArrayList<Person>>() {
                        }.getType()
                );
    }
}

