package miki;

import miki.task.*;

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
            "Your ChatBot assistant. \n"
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
                System.out.println("Terminating Session. Goodbye.\n");
                System.out.println("____________________________________________________________\n");
                break;
            }
            handleInput(input);

            System.out.println("____________________________________________________________\n");
        }
        sc.close();
    }

    public static void handleInput(String line) {
        if (line.length() == 0) {
            System.out.println("No command received. Try again.");
            return;
        }
        String[] inputs = line.split(" ");
        if (inputs[0].toLowerCase().equals("list")) {
            if (inputs.length != 1) {
                System.out.println("Error Occured.\nPlease pass in the correct number of arguments.");
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
                System.out.println("Error Occured.\nPlease pass in the correct number of arguments.");
                return;
            }
            int taskNumber = Integer.parseInt(inputs[1]);
            if (taskNumber > taskCount) {// check task number is within the range of available tasks
                System.out.println("Error Occured.\nThe task you requested for does not exist.");
                return;
            }
            Task task = tasks[taskNumber - 1];
            if (inputs[0].toLowerCase().equals("mark")) {
                if (task.checkCompleted()) {
                    System.out.println(
                            "Task " + taskNumber + " has already been completed.\n");
                    return;
                }
                task.toggleCompletion();
                System.out.println(
                        "Task " + taskNumber + " completed.\n"
                                + task.toString());
            } else {
                if (!task.checkCompleted()) {
                    System.out.println(
                            "Task " + taskNumber + " has yet to be completed.\n");
                    return;
                }
                task.toggleCompletion();
                System.out.println(
                        "Task " + taskNumber + " is not completed.\n"
                                + task.toString());
            }
            return;
        }
        // add input as a task
        if (inputs[0].toLowerCase().equals("event")) {
            int startIndex = -1, endIndex = -1;
            for (int i = inputs.length - 4; i > 1; i--) {
                if (inputs[i].equals("/from")) {
                    if (startIndex != -1) {
                        System.out.println(
                                "Error Occured.\nThe event task you wrote has too many start timings.");
                        return;
                    }
                    startIndex = i;
                }
            }
            if (startIndex == -1) {
                System.out.println(
                        "Error Occured.\nThe event task you wrote has no start timing.");
                return;
            }
            for (int i = startIndex; i > 1; i++) {
                if (inputs[i].equals("/to")) {
                    if (endIndex != -1) {
                        System.out.println(
                                "Error Occured.\nThe event task you wrote has too many end timings.");
                        return;
                    }
                    endIndex = i;
                }
            }
            if (endIndex == -1) {
                System.out.println(
                        "Error Occured.\nThe event task you wrote has no end timing.");
                return;
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
            return;

        } else if (inputs[0].toLowerCase().equals("deadline")) {
            int byIndex = -1;
            for (int i = inputs.length - 2; i > 1; i--) {
                if (inputs[i].equals("/by")) {
                    if (byIndex != -1) {
                        System.out.println(
                                "Error Occured.\nThe deadline task you wrote has too many deadlines.");
                        return;
                    }
                    byIndex = i;
                }
            }
            if (byIndex == -1) {
                System.out.println(
                        "Error Occured.\nThe deadline task you wrote does not have a deadline.");
                return;
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
            return;
        } else if (inputs[0].toLowerCase().equals("todo")) {
            Task task = new ToDo(line);

            tasks[taskCount] = task;
            taskCount++;
            return;
        }
        else {
            System.out.println("Invalid Input.\n" 
                + "Please follow the following formats to set tasks:\n"
                + "ToDo    : ToDo {TASK DESCRIPTION}\n"
                + "Deadline: Deadline {TASK DESCRIPTION} /by {DEADLINE}\n"
                + "Event   : Event {TASK DESCRIPTION} /from {START} /to {END}");
        }
    }
}
