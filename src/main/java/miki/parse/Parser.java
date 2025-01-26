package miki.parse;

import miki.command.*;
import miki.exception.*;

public class Parser {
    public static Command handleUserCommand(String line) throws MikiException {
        // empty input
        if (line.length() == 0) {
            throw new NoCommandException("No command received Dawg. Try that again.");
        }
        String[] tokens = line.split(" ");

        // list
        if (tokens[0].toLowerCase().equals("list")) {
            if (tokens.length != 1) {
                throw new MikiException("List command has no arguments. Please try again.");
            }
            return new ListCommand();
        }
        // mark
        if (tokens[0].toLowerCase().equals("mark")) {
            if (tokens.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Please pass in the correct number of arguments.\n"
                        + "Format   :   mark {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new MarkCommand(taskNumber);
        }

        // unmark
        if (tokens[0].toLowerCase().equals("unmark")) {
            if (tokens.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Please pass in the correct number of arguments for the Unmark command\n"
                        + "Format   :   unmark {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new UnmarkCommand(taskNumber);
        }

        // delete
        if (tokens[0].toLowerCase().contains("delete")) {
            if (tokens.length != 2) {
                System.out.println(tokens.length);
                throw new DeleteFailedException("Please pass in the correct number of arguments.\n"
                        + "Format   :   delete {TASKNUMBER}");
            }
            int taskNumber = Integer.parseInt(tokens[1]);
            return new DeleteCommand(taskNumber);
        }

        // parse input and return relevant task command
        if (tokens[0].toLowerCase().equals("event")) {
            int startIndex = -1, endIndex = -1;
            for (int i = tokens.length - 1; i > 0; i--) {
                if (tokens[i].equals("/from")) {
                    if (startIndex != -1) {
                        throw new EventException("The event task you wrote has too many start timings.\n");
                    }
                    startIndex = i;
                }
            }
            if (startIndex == -1) {
                throw new EventException("The event task you wrote has no start date.\n");
            }
            for (int i = startIndex; i < tokens.length; i++) {
                if (tokens[i].equals("/to")) {
                    if (endIndex != -1) {
                        throw new EventException("The event task you wrote has too many end timings.\n");
                    }
                    endIndex = i;
                }
            }
            if (endIndex == -1) {
                throw new EventException("The event task you wrote has no end timing.\n");
            }
            if (startIndex == 1) {
                throw new EventException("Your task lacks a description.\n");
            }
            StringBuilder descBuilder = new StringBuilder();
            for (int i = 1; i < startIndex; i++) {
                descBuilder.append(tokens[i]).append(" ");
            }
            StringBuilder startBuilder = new StringBuilder();
            for (int i = startIndex + 1; i < endIndex; i++) {
                startBuilder.append(tokens[i]).append(" ");
            }
            StringBuilder endBuilder = new StringBuilder();
            for (int i = endIndex + 1; i < tokens.length; i++) {
                endBuilder.append(tokens[i]).append(" ");
            }
            return new AddEventCommand(descBuilder.toString().trim(),
                    startBuilder.toString().trim(),
                    endBuilder.toString().trim());

        } else if (tokens[0].toLowerCase().equals("deadline")) {
            int byIndex = -1;
            for (int i = tokens.length - 2; i > 0; i--) {
                if (tokens[i].equals("/by")) {
                    if (byIndex != -1) {
                        throw new DeadlineException("The deadline task you wrote has too many deadlines.");
                    }
                    byIndex = i;
                }
            }
            if (byIndex == -1) {
                throw new DeadlineException("The deadline task you wrote does not have a deadline.");
            }
            if (byIndex == 1) {
                throw new DeadlineException("Your task lacks a description.\n");
            }

            StringBuilder descBuilder = new StringBuilder();
            for (int i = 1; i < byIndex; i++) {
                descBuilder.append(tokens[i]).append(" ");
            }
            StringBuilder deadlineBuilder = new StringBuilder();
            for (int i = byIndex + 1; i < tokens.length; i++) {
                deadlineBuilder.append(tokens[i]).append(" ");
            }
            return new AddDeadlineCommand(descBuilder.toString().trim(),
                    deadlineBuilder.toString().trim());

        } else if (tokens[0].toLowerCase().equals("todo")) {
            if (tokens.length == 1) {
                throw new ToDoException("Your task lacks a description.\n");
            }
            StringBuilder descBuilder = new StringBuilder();
            for (int i = 1; i < tokens.length; i++) {
                descBuilder.append(tokens[i]).append(" ");
            }
            return new AddToDoCommand(descBuilder.toString().trim());
        }

        throw new InvalidTaskException("What are you saying Dawg.\n");
    }
}
