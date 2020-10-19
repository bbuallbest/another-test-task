package com.testtask;

import com.testtask.model.Account;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestTaskTest {

    private TestTask testTask;

    @Before
    public void setUp() {
        testTask = new TestTask(TestAccountsGenerator.generate());
    }

    @Test
    public void findRichestPerson_happyPath() {
        Account account = testTask.findRichestPerson()
                .orElse(null);

        assertNotNull(account);
        assertEquals(BigDecimal.valueOf(13000L), account.getBalance());
        assertEquals("Amelia", account.getFirstName());
        assertEquals("Johnson", account.getLastName());
        assertEquals("amelia.johnson@yahoo.com", account.getEmail());
    }

    @Test
    public void partitionMaleAccounts_happyPath() {
        Map<Boolean, List<Account>> partitionedAccounts = testTask.partitionMaleAccounts();

        assertEquals(2, partitionedAccounts.keySet().size());
        assertEquals(3, partitionedAccounts.get(Boolean.TRUE).size());
        assertEquals(2, partitionedAccounts.get(Boolean.FALSE).size());
    }

    @Test
    public void groupAccountsByEmailDomain_happyPath() {
        Map<String, List<Account>> accounts = testTask.groupAccountsByEmailDomain();

        assertEquals(4, accounts.keySet().size());
        assertEquals(2, accounts.get("gmail.com").size());
        assertEquals(1, accounts.get("yahoo.com").size());
        assertEquals(1, accounts.get("bing.com").size());
        assertEquals(1, accounts.get("test.com").size());
    }

    @Test
    public void getCharacterFrequencyIgnoreCaseInFirstAndLastNames_happyPath() {
        Map<Character, Long> frequency = testTask.getCharacterFrequencyIgnoreCaseInFirstAndLastNames();

        assertEquals(18, frequency.keySet().size());
        assertEquals(Long.valueOf(7), frequency.get('i'));
        assertEquals(Long.valueOf(6), frequency.get('o'));
        assertEquals(Long.valueOf(5), frequency.get('l'));
        assertEquals(Long.valueOf(4), frequency.get('r'));
        assertEquals(Long.valueOf(4), frequency.get('s'));
        assertEquals(Long.valueOf(4), frequency.get('n'));
        assertEquals(Long.valueOf(3), frequency.get('e'));
        assertEquals(Long.valueOf(3), frequency.get('m'));
        assertEquals(Long.valueOf(3), frequency.get('j'));
        assertEquals(Long.valueOf(3), frequency.get('h'));
        assertEquals(Long.valueOf(2), frequency.get('v'));
        assertEquals(Long.valueOf(2), frequency.get('w'));
        assertEquals(Long.valueOf(1), frequency.get('c'));
        assertEquals(Long.valueOf(1), frequency.get('k'));
        assertEquals(Long.valueOf(1), frequency.get('y'));
        assertEquals(Long.valueOf(1), frequency.get('t'));
        assertEquals(Long.valueOf(1), frequency.get('b'));
    }
}
