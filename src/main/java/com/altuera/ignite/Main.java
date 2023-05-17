package com.altuera.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

import java.util.UUID;

public class Main {
    static int SIZE = 100_000;
    public static void main(String[] args) throws InterruptedException {
        Ignite ign = Ignition.start();

        IgniteClient igniteClient = Ignition.startClient(new ClientConfiguration().setAddresses("127.0.0.1:10800"));

        IgniteCache<UUID, Person> personCache = ign.createCache("Person");

        for (int i = 0; i < SIZE; i++) {
            Person person = PersonGenerator.getInstance().generateRandomPerson();
            personCache.put(person.getId(), person);
        }


        Double result = ign.compute().<Void, Double>execute(PersonAverageAgeTask.class, null);

        System.out.println("Average person = " + result);
    }
}
