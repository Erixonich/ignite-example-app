package com.altuera.ignite;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
public class Person {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthday;
    private final String ssn;
}