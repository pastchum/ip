package miki;

import miki.task.*;

import java.util.Scanner;

public class Miki {
    private static String logo = "      __  _________ __ ____\n" + //
            "     /  |/  /  _/ //_//  _/\n" + //
            "    / /|_/ // // ,<   / /  \n" + //
            "   / /  / // // /| |_/ /   \n" + //
            "  /_/  /_/___/_/ |_/___/   "; //

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

    public static void handleInput(String input) {
        if (input.toLowerCase().equals("list")) {
            if (taskCount == 0) { // account for no tasks
                System.out.println("There are no tasks to display.");
                return;
            }
            System.out.println("List of tasks: "); // print out tasks 1 by 1
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
            return;
        }
        // add input as a task
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("Added new Task: " + input);
        return;
    }
}
