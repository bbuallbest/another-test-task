package com.testtask;

import com.testtask.model.Account;
import com.testtask.model.Sex;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class TestTask {

    private static final String EMAIL_DOMAIN_DELIMITER = "@";

    private Collection<Account> accounts;

    TestTask(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Returns {@link Optional} that contains an {@link Account} with the max value of balance
     *
     * @return account with max balance wrapped with optional
     */
    public Optional<Account> findRichestPerson() {
        return accounts.stream()
                .filter(account -> nonNull(account.getBalance()))
                .max(Comparator.comparing(Account::getBalance, BigDecimal::compareTo));
    }

    /**
     * Returns a map that separates all accounts into two lists - male and female. Map has two keys {@code true} indicates
     * male list, and {@code false} indicates female list.
     *
     * @return a map where key is true or false, and value is list of male, and female accounts
     */
    public Map<Boolean, List<Account>> partitionMaleAccounts() {
        return accounts.stream()
                .filter(account -> nonNull(account.getSex()))
                .collect(Collectors.groupingBy(account -> Sex.MALE == account.getSex()));
    }

    /**
     * Returns a {@link Map} that stores accounts grouped by its email domain. A map key is {@link String} which is an
     * email domain like "gmail.com". And the value is a {@link List} of {@link Account} objects with a specific email domain.
     *
     * @return a map where key is an email domain and value is a list of all account with such email
     */
    public Map<String, List<Account>> groupAccountsByEmailDomain() {
        return accounts.stream()
                .filter(TestTask::nonNullableValidEmail)
                .collect(Collectors.groupingBy(TestTask::getEmailDomain));
    }

    private static boolean nonNullableValidEmail(Account account) {
        String email = account.getEmail();
        return nonNull(email) && email.contains(EMAIL_DOMAIN_DELIMITER);
    }

    private static String getEmailDomain(Account account) {
        return account.getEmail().split(EMAIL_DOMAIN_DELIMITER)[1];
    }

    /**
     * Returns a {@link Map} where key is a letter {@link Character}, and value is a number of its occurrences ignoring
     * case, in all {@link Account#getFirstName()} and {@link Account#getLastName()}. All letters should stored in lower case.
     *
     * @return a map where key is a letter and value is its count ignoring case in all first and last names
     */
    public Map<Character, Long> getCharacterFrequencyIgnoreCaseInFirstAndLastNames() {
        return accounts.stream()
                .flatMap(TestTask::toLowerCaseNameStream)
                .flatMap(TestTask::toCharacterStream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static Stream<String> toLowerCaseNameStream(Account account) {
        return Stream.of(account.getFirstName(), account.getLastName())
                .filter(Objects::nonNull)
                .map(String::toLowerCase);
    }

    private static Stream<Character> toCharacterStream(String name) {
        return name.chars().mapToObj(i -> (char) i);
    }
}

