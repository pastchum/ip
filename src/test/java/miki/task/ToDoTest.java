package miki.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {
    @Test
    public void testToDo() {
        try {
            // Test Task
            Task task = new ToDo("Test");

            assertEquals("[T] " + "[ ]  Test" + "\n", task.toString());
        } catch (Error e) {
            fail();
        }
    }

    @Test
    public void testToDoToStorageFormat() {
        try {
            // Test Task
            Task task = new ToDo("Test");

            assertEquals("T | 0 | Test", task.toStorageFormat());
        } catch (Error e) {
            fail();
        }
    }
}
