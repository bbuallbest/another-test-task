package com.testtask;

import com.testtask.model.Account;
import com.testtask.model.Sex;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class TestAccountsGenerator {

    private static final List<String> FIRST_NAMES = asList("Oliver", "Amelia", "Jack", "Olivia", "Harry");
    private static final List<String> LAST_NAMES = asList("Smith", "Johnson", "Williams", "Brown", "Jones");
    private static final List<String> EMAIL_DOMAINS = asList("gmail.com", "yahoo.com", "bing.com", "gmail.com", "test.com");

    public static List<Account> generate() {
        return IntStream.range(0, FIRST_NAMES.size())
                .boxed()
                .map(TestAccountsGenerator::generateAccount)
                .collect(Collectors.toList());
    }

    private static Account generateAccount(Integer index) {
        String firstName = FIRST_NAMES.get(index);
        String lastName = LAST_NAMES.get(index);
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + EMAIL_DOMAINS.get(index);
        Sex sex = index % 2 == 0 ? Sex.MALE : Sex.FEMALE;
        int balance = (firstName.length() + lastName.length()) * 1000;

        return Account.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .sex(sex)
                .balance(BigDecimal.valueOf(balance))
                .build();
    }
}
