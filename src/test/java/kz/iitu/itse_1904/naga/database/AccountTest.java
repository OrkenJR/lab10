package kz.iitu.itse_1904.naga.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class AccountTest {
    Account account = new Account();

    @Test
    void testSetId() {
        ReflectionTestUtils.setField(account, "id", 1L);
        assertTrue(account.getId().equals(1L));
    }

    @Test
    void testSetNumber() {
        ReflectionTestUtils.setField(account, "number", "test");
        assertEquals(account.getNumber(), "test");
    }

    @Test
    void testSetAmount() {
        ReflectionTestUtils.setField(account, "amount", 1L);
        assertTrue(account.getAmount().equals(1L));
    }

    @Test
    void testSetBlocked() {
        ReflectionTestUtils.setField(account, "isBlocked", true);
        assertTrue(account.isBlocked());
    }

    @Test
    void testEquals() {
        boolean result = account.equals("o");
        Assertions.assertEquals(false, result);
    }



    @Test
    void testHashCode() {
        int result = account.hashCode();
        ReflectionTestUtils.setField(account, "isBlocked", true);
        Assertions.assertEquals(account.hashCode(), result);
    }

    @Test
    void testToString() {
        String result = account.toString();
        Assertions.assertEquals("Account(id=null, number=null, amount=null, isBlocked=false, user=null)", result);
    }

    @Test
    void testBuilder() {

        Account.AccountBuilder result = Account.builder().amount(100L);
        assertTrue(!Account.builder().toString().equals(result.toString()));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme