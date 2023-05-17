package com.altuera.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import java.util.UUID;

public class Main {
    static int SIZE = 100_000;
    public static void main(String[] args) {
        Ignite ign = Ignition.start();

        IgniteCache<UUID, Person> personCache = ign.createCache("Person");

        for (int i = 0; i < SIZE; i++) {
            Person person = PersonGenerator.getInstance().generateRandomPerson();
            personCache.put(person.getId(), person);
        }


        ? result = ign.compute().execute(PersonAverageAgeTask.class, ?);

        System.out.println(result);
    }
}
