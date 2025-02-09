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
    public void showLoadingError() {
        System.out.println("");
    }

    /**
     * Method to show line
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Method to show intro
     */
    public void showIntro() {
        String logo = "      __  _________ __ ____\n" + //
                "     /  |/  /  _/ //_//  _/\n" + //
                "    / /|_/ // // ,<   / /  \n" + //
                "   / /  / // // /| |_/ /   \n" + //
                "  /_/  /_/___/_/ |_/___/   \n" +
                "                           \n" +
                "          ฅ^•ﻌ•^ฅ         "; //

        String intro = "Hello from \n"
                + logo +
                "\n" +
                "Your ChatBot assistant Dawg :). \n"
                +
                "\n"
                + "Please enter a command to start: \n";

        showLine();
        System.out.println(intro);
        showLine();
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
    public void showErrorMessage(String errorMessage) {
        System.out.println("Error Occured Dawg.\n" + errorMessage);
    }

    /**
     * Method to show task added
     *
     * @param task     Task object containing task added
     * @param taskSize Integer containing size of task list
     */
    public void showTaskAdded(Task task, int taskSize) {
        System.out.println(
                task.getTaskType().toString()
                        + " task has been added Dawg.\n"
                        + task.toString() + "\n"
                        + "You now have " + taskSize + " task" + (taskSize > 1 ? "s" : "") + ".\n"
                        + "Get to work Dawg.");
    }

    /**
     * Method to show task deleted
     *
     * @param task       Task object containing task deleted
     * @param taskNumber Integer containing task number
     */
    public void showTaskDeleted(Task task, int taskNumber) {
        System.out.println(
                "Task " + taskNumber + " has been deleted Dawg.\n"
                        + task.toString());
    }

    /**
     * Method to show list of tasks
     *
     * @param tasks TaskList object containing list of tasks
     */
    public void showList(TaskList tasks) {
        if (tasks.getSize() == 0) { // account for no tasks
            System.out.println("There are no tasks to display.");
            return;
        }
        System.out.println("List of tasks: "); // print out tasks 1 by 1
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    /**
     * Method to show task marked
     *
     * @param task       Task object containing task marked
     * @param taskNumber Integer containing task number
     */
    public void showTaskCompletion(Task task, int taskNumber) {
        boolean isCompleted = task.checkCompleted();
        System.out.println(
                "Task " + taskNumber + (isCompleted ? " completed.\n" : " is not completed.\n")
                        + task.toString());
    }

    /**
     * Method to show task found by search
     *
     * @param tasks TaskList object containing tasks found
     */
    public void showFoundTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println("No tasks found Dawg.");
            return;
        }
        System.out.println("Found tasks: ");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
        }
    }

    /**
     * Method to show exit
     */
    public void showExit() {
        System.out.println("End of session Dawg. Goodbye Dawg.\n");
        showLine();
        sc.close();
    }
}
