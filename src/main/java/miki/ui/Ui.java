package miki.ui;

import java.util.Scanner;

import miki.task.Task;
import miki.task.TaskList;

/**
 * Ui class to handle all user interface interactions
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Method to show loading error
     */
    public String showLoadingError() {
        String error = "Error loading tasks from file. Starting with an empty task list.";
        System.out.println(error);
        return error;
    }

    /**
     * Method to show line
     */
    public String showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
        return line;
    }

    /**
     * Method to show intro
     */
    public String showIntro() {
        String logo = "      __  _________ __ ____\n"
                + "     /  |/  /  _/ //_//  _/\n"
                + "    / /|_/ // // ,<   / /  \n"
                + "   / /  / // // /| |_/ /   \n"
                + "  /_/  /_/___/_/ |_/___/   \n"
                + "                           \n"
                + "          ฅ^•ﻌ•^ฅ         ";

        String intro = "Hello from \n"
                + logo
                + "\n"
                + "Your ChatBot assistant Dawg :). \n"
                + "\n"
                + "Please enter a command to start: \n";

        showLine();
        System.out.println(intro);
        showLine();
        return intro;
    }

    /**
     * Method to read command
     *
     * @return String containing user's input command
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Method to show error message
     *
     * @param errorMessage String containing error message
     */
    public String showErrorMessage(String errorMessage) {
        System.out.println("Error Occured Dawg.\n" + errorMessage);
        return "Error Occured Dawg.\n" + errorMessage;
    }

    /**
     * Method to show task added
     *
     * @param task        Task object containing task added
     * @param sizeOfTasks Integer containing size of task list
     */
    public String showTaskAdded(Task task, int sizeOfTasks) {
        String output = task.getTaskType().toString()
                + " task has been added Dawg.\n"
                + task.toString()
                + "You now have " + sizeOfTasks + " tasks in the list Dawg.";
        System.out.println(output);
        return output;
    }

    /**
     * Method to show task deleted
     *
     * @param task      Task object containing task deleted
     * @param taskIndex Integer containing task number
     */
    public String showTaskDeleted(Task task, int taskIndex) {
        String output = "Task " + taskIndex + " has been deleted Dawg.\n"
                + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Method to show list of tasks
     *
     * @param tasks TaskList object containing list of tasks
     */
    public String showList(TaskList tasks) {
        if (tasks.getSize() == 0) { // account for no tasks
            String output = "No tasks in the list Dawg.";
            System.out.println(output);
            return output;
        }
        String output = "Here are the tasks in your list Dawg:\n";
        // add tasks to output string 1 by 1
        for (int i = 0; i < tasks.getSize(); i++) {
            output += (i + 1) + ". " + tasks.getTask(i).toString() + "\n";
        }
        System.out.println(output);
        return output;
    }

    /**
     * Method to show task marked
     *
     * @param task      Task object containing task marked
     * @param taskIndex Integer containing task number
     */
    public String showTaskCompletion(Task task, int taskIndex) {
        boolean isCompleted = task.checkCompleted();
        String output = "Task " + taskIndex + (isCompleted ? " completed.\n" : " is not completed.\n")
                + task.toString();
        System.out.println(output);
        return output;
    }

    /**
     * Method to show exit
     */
    public String showExit() {
        String message = "End of session Dawg. Goodbye Dawg.\n";
        System.out.println(message);
        showLine();
        sc.close();
        return message;
    }
}
