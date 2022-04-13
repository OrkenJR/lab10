package kz.iitu.itse_1904.naga.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class UserTest {
    User user = new User();

    @Test
    void testSetId() {
        ReflectionTestUtils.setField(user, "id", 1L);
        assertTrue(user.getId().equals(1L));
    }

    @Test
    void testSetEmail() {
        ReflectionTestUtils.setField(user, "email", "test");
        assertEquals(user.getEmail(), "test");
    }

    @Test
    void testSetFirstName() {
        ReflectionTestUtils.setField(user, "firstName", "test");
        assertEquals(user.getFirstName(), "test");
    }

    @Test
    void testSetLastName() {
        ReflectionTestUtils.setField(user, "lastName", "test");
        assertEquals(user.getLastName(), "test");
    }

    @Test
    void testSetPhone() {
        ReflectionTestUtils.setField(user, "phone", "test");
        assertEquals(user.getPhone(), "test");
    }

    @Test
    void testSetUsername() {
        ReflectionTestUtils.setField(user, "username", "test");
        assertEquals(user.getUsername(), "test");
    }

    @Test
    void testSetPassword() {
        ReflectionTestUtils.setField(user, "password", "test");
        assertEquals(user.getPassword(), "test");
    }

    @Test
    void testEquals() {
        boolean result = user.equals("o");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testHashCode() {
        int result = user.hashCode();
        ReflectionTestUtils.setField(user, "password", "test");
        Assertions.assertEquals(user.hashCode(), result);
    }

    @Test
    void testToString() {
        String result = user.toString();
        Assertions.assertEquals("User(id=null, email=null, firstName=null, lastName=null, phone=null, username=null, password=null, roles=null)", result);
    }

    @Test
    void testBuilder() {
        User.UserBuilder result = User.builder();
        assertTrue(User.builder().toString().equals(result.toString()));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme