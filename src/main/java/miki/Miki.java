package miki;

import miki.task.*;
import miki.exception.*;

import java.util.Scanner;

public class Miki {
    private static String logo = "      __  _________ __ ____\n" + //
            "     /  |/  /  _/ //_//  _/\n" + //
            "    / /|_/ // // ,<   / /  \n" + //
            "   / /  / // // /| |_/ /   \n" + //
            "  /_/  /_/___/_/ |_/___/   \n" +
            "                           \n" +
            "          ฅ^•ﻌ•^ฅ         "; //

    private static String intro = "____________________________________________________________\n" + //
            "Hello from \n"
            + logo +
            "\n" +
            "Your ChatBot assistant Dawg :). \n"
            +
            "\n"
            + "Please enter a command to start: \n";

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");
            if (input.toLowerCase().contains("bye")) {
                System.out.println("End of session Dawg. Goodbye Dawg.\n");
                System.out.println("____________________________________________________________\n");
                break;
            }
            try {
                handleInput(input);
            } catch (MikiException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________________________________\n");
        }
        sc.close();
    }

    public static void handleInput(String line) throws MikiException {
        if (line.length() == 0) {
            throw new NoCommandException("No command received Dawg. Try that again.");
        }
        String[] inputs = line.split(" ");
        if (inputs[0].toLowerCase().equals("list")) {
            if (inputs.length != 1) {
                System.out.println("Error Occured Dawg.\nPlease pass in the correct number of arguments.");
            }
            if (taskCount == 0) { // account for no tasks
                System.out.println("There are no tasks to display.");
                return;
            }
            System.out.println("List of tasks: "); // print out tasks 1 by 1
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
            return;
        } else if (inputs[0].toLowerCase().contains("mark")) {
            if (inputs.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Error Occured Dawg.\nPlease pass in the correct number of arguments.");
            }
            int taskNumber = Integer.parseInt(inputs[1]);
            if (taskNumber > taskCount) {// check task number is within the range of available tasks
                throw new CheckException("Error Occured Dawg.\nThe task you requested for does not exist.");
            }
            Task task = tasks[taskNumber - 1];
            if (inputs[0].toLowerCase().equals("mark")) {
                if (task.checkCompleted()) {
                    throw new CheckException(
                            "Task " + taskNumber + " has already been completed.\n");
                }
                task.toggleCompletion();
                System.out.println(
                        "Task " + taskNumber + " completed.\n"
                                + task.toString());
            } else {
                if (!task.checkCompleted()) {
                    throw new CheckException(
                            "Task " + taskNumber + " has yet to be completed.\n");
                }
                task.toggleCompletion();
                System.out.println(
                        "Task " + taskNumber + " is not completed.\n"
                                + task.toString());
            }
            return;
        }
        if (taskCount == 100) {
            throw new TooManyTasksException("Too many dawg!\n" + "Go delete some.");
        }

        // add input as a task
        if (inputs[0].toLowerCase().equals("event")) {
            int startIndex = -1, endIndex = -1;
            for (int i = inputs.length - 1; i > 0; i--) {
                if (inputs[i].equals("/from")) {
                    if (startIndex != -1) {
                        throw new EventException("The event task you wrote has too many start timings.\n");
                    }
                    startIndex = i;
                }
            }
            if (startIndex == -1) {
                throw new EventException("The event task you wrote has no start date.\n");
            }
            for (int i = startIndex; i < inputs.length; i++) {
                if (inputs[i].equals("/to")) {
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
                descBuilder.append(inputs[i]).append(" ");
            }
            StringBuilder startBuilder = new StringBuilder();
            for (int i = startIndex + 1; i < endIndex; i++) {
                startBuilder.append(inputs[i]).append(" ");
            }
            StringBuilder endBuilder = new StringBuilder();
            for (int i = endIndex + 1; i < inputs.length; i++) {
                endBuilder.append(inputs[i]).append(" ");
            }
            Task task = new Event(descBuilder.toString().trim(),
                    startBuilder.toString().trim(),
                    endBuilder.toString().trim());
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "Event task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            return;

        } else if (inputs[0].toLowerCase().equals("deadline")) {
            int byIndex = -1;
            for (int i = inputs.length - 2; i > 0; i--) {
                if (inputs[i].equals("/by")) {
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
                descBuilder.append(inputs[i]).append(" ");
            }
            StringBuilder deadlineBuilder = new StringBuilder();
            for (int i = byIndex + 1; i < inputs.length; i++) {
                deadlineBuilder.append(inputs[i]).append(" ");
            }
            Task task = new Deadline(descBuilder.toString().trim(), deadlineBuilder.toString().trim());
            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "Deadline task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            return;
        } else if (inputs[0].toLowerCase().equals("todo")) {
            Task task = new ToDo(line);

            tasks[taskCount] = task;
            taskCount++;
            System.out.println(
                    "ToDo task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + taskCount + " task" + (taskCount > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            return;
        } else {
            throw new InvalidTaskException("What are you saying Dawg.\n");
        }
    }
}
