package com.graphql.query;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class PersonQueryTest {
    private GraphQL graphQL;

     @Before
    public void init() {
        GraphQLSchema schema = SchemaParser.newParser().file("graphqls/users.graphqls")
                .resolvers(new PersonQuery())
                .build().makeExecutableSchema();
        graphQL = GraphQL.newGraphQL(schema).build();
    }

      @Test
    public void testQuery() {
        String query = "Query";
        Map<String, Object> variables = new HashMap<>();

        variables.put("id",1);
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).variables(variables).build();
        ExecutionResult executionResult = graphQL.execute(executionInput);
        System.out.println((String) executionResult.getData());
    }




}
