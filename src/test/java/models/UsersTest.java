package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsersTest {

    @Test
    public void instanceOfObjecct() {
        Users newUser = new Users("me", "we", 12);
        assertTrue(newUser instanceof Users);
    }
}