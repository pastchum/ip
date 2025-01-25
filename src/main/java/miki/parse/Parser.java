package miki.parse;

import miki.exception.MikiException;
import miki.task.*;

public class Parser {
    public static Task parseTaskFromFile(String line) throws MikiException {
        String[] tokens = line.split(" | ");
        try {
            Task task = null;
            if (tokens[0].equals("D")) {
                task = new Deadline(tokens[2], tokens[3]);
            } else if (tokens[0].equals("E")) {
                task = new Event(tokens[2], tokens[3], tokens[4]);
            } else {
                task = new ToDo(tokens[2]);
            }
            if (tokens[1].equals("1") && task != null) {
                task.toggleCompletion();
            }
            return task;
        } catch (Exception e) {
            throw new MikiException(e.getMessage());
        }
    }
}
