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
        // TODO: switch for task logic
        tasks[taskCount] = new Task(line);
        taskCount++;
        System.out.println("Added new Task: " + line);
        return;
    }
}
