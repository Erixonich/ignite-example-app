package com.altuera.ignite;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.lang.IgniteBiPredicate;

import javax.cache.Cache;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PersonDao {
    IgniteCache<UUID, Person> cache;

    public PersonDao(IgniteCache<UUID, Person> cache) {
        this.cache = cache;
    }

    public Person findById(UUID id) {
        return cache.get(id);
    }

    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) {
        IgniteBiPredicate<UUID, Person> pred = (id, person) ->
                firstName.equals(person.getFirstName()) && lastName.equals(person.getLastName());

        QueryCursor<Cache.Entry<UUID, Person>> cursor = cache.query(new ScanQuery<>(pred));

        return cursor.getAll().stream()
                .map(Cache.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void insert(Person person) {
        cache.put(person.getId(), person);
    }

    public void updatePerson(Person person) {
        cache.replace(person.getId(), person);
    }

    public boolean removePersonById(UUID id) {
        return cache.remove(id);
    }
}
