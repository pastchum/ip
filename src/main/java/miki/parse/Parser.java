package miki.parse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import miki.command.*;
import miki.exception.*;

/**
 * Parser class handles the parsing of user input.
 */
public class Parser {
    /**
     * Parses the user input and returns the relevant command.
     *
     * @param line user input.
     * @return relevant command.
     * @throws MikiException if the user input is invalid.
     */
    public static Command handleUserCommand(String line) throws MikiException {
        // empty input
        if (line.isEmpty()) {
            throw new NoCommandException("No command received Dawg. Try that again.");
        }

        // list
        if (line.toLowerCase().startsWith("list")) {
            if (!line.toLowerCase().equals("list")) {
                throw new MikiException("List command has no arguments. Please try again.");
            }
            return new ListCommand();
        }

        // mark
        if (line.toLowerCase().startsWith("mark")) {
            String[] tokens = line.split(" ");
            if (tokens.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Please pass in the correct number of arguments.\n"
                        + "Format   :   mark {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new MarkCommand(taskNumber);
        }

        // unmark
        if (line.toLowerCase().startsWith("unmark")) {
            String[] tokens = line.split(" ");
            if (tokens.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Please pass in the correct number of arguments for the Unmark command\n"
                        + "Format   :   unmark {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new UnmarkCommand(taskNumber);
        }

        // delete
        if (line.toLowerCase().startsWith("delete")) {
            String[] tokens = line.split(" ");
            if (tokens.length != 2) {
                throw new DeleteFailedException("Please pass in the correct number of arguments.\n"
                        + "Format   :   delete {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new DeleteCommand(taskNumber);
        }

        // find
        if (line.toLowerCase().startsWith("find")) {
            String[] tokens = line.split(" ", 2);
            if (tokens[1].isEmpty()) {
                throw new MikiException("Please pass in the correct number of arguments.\n"
                        + "Format   :   find {KEYWORDS}");
            }
            return new FindCommand(tokens[1]);
        }

        // parse input and return relevant task command
        if (line.toLowerCase().startsWith("event")) {
            String[] tokens = line.split(" ", 2);
            if (tokens.length == 1) {
                throw new EventException("Your task lacks details.\n");
            }
            String[] taskDetails = tokens[1].split("/from|/to", 3);

            // parse start date
            LocalDateTime startDate = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm ");
                startDate = LocalDateTime.parse(taskDetails[1], formatter);
            } catch (Exception e) {
                throw new EventException("The start date format is invalid.");
            }

            // parse end date
            LocalDateTime endDate = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm ");
                endDate = LocalDateTime.parse(taskDetails[2], formatter);
                if (endDate.isAfter(startDate)) {
                    throw new EventException("The end date is before the start date.");
                }
            } catch (Exception e) {
                throw new EventException("The end date format is invalid.");
            }

            return new AddEventCommand(taskDetails[0].trim(),
                    startDate,
                    endDate);

        } else if (line.toLowerCase().startsWith("deadline")) {
            String[] tokens = line.split(" ", 2);
            if (tokens.length == 1) {
                throw new DeadlineException("Your task lacks details.\n");
            }
            String[] taskDetails = tokens[1].split("/by", 2);

            // parse deadline date
            LocalDateTime deadlineDate = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm ");
                deadlineDate = LocalDateTime.parse(taskDetails[1], formatter);
            } catch (Exception e) {
                throw new DeadlineException("The deadline date format is invalid.");
            }
            return new AddDeadlineCommand(taskDetails[0].trim(),
                    deadlineDate);

        } else if (line.toLowerCase().startsWith("todo")) {
            String tokens[] = line.split(" ", 2);
            if (tokens.length == 1) {
                throw new ToDoException("Your task lacks a description.\n");
            }

            return new AddToDoCommand(tokens[1].trim());
        }

        throw new InvalidTaskException("What are you saying Dawg.\n");
    }
}
