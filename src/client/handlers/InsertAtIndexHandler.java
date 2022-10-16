package client.handlers;


import client.client.Client;
import person.Person;

import java.io.IOException;
import java.io.Serializable;

public class InsertAtIndexHandler implements Handler, Serializable {

    Client client;
    public InsertAtIndexHandler(Client client) {
        this.client = client;
    }

    @Override
    public String handle(String[] args) throws IOException, ClassNotFoundException {
        if (args == null) {
            return "Этой команде нужен аргумент (id).";
        }
        if(args.length>1){
            return "У этой команды не может быть так много аргументов.";
        }
        String id = args[0];
        String response = client.send("is_exist", id);
        if (response.equals("true")) {
            return "Человек с таким id уже существует.";
        }
        Person person = new Person(id);
        return client.send("insert_at_index", person);
    }
}
