package miki.ui;

import java.util.Scanner;

import miki.task.Task;
import miki.task.TaskList;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLoadingError() {
        System.out.println("");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

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

    public String readCommand() {
        return sc.nextLine();
    }

    public void showErrorMessage(String errorMessage) {
        System.out.println("Error Occured Dawg.\n" + errorMessage);
    }

    public void showTaskAdded(Task task, int taskSize) {
        System.out.println(
                task.getTaskType().toString()
                        + " task has been added Dawg.\n"
                        + task.toString() + "\n"
                        + "You now have " + taskSize + " task" + (taskSize > 1 ? "s" : "") + ".\n"
                        + "Get to work Dawg.");
    }

    public void showTaskDeleted(Task task, int taskNumber) {
        System.out.println(
                "Task " + taskNumber + " has been deleted Dawg.\n"
                        + task.toString());
    }

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

    public void showTaskCompletion(Task task, int taskNumber) {
        boolean isCompleted = task.checkCompleted();
        System.out.println(
                "Task " + taskNumber + (isCompleted ? " completed.\n" : " is not completed.\n")
                        + task.toString());
    }

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

    public void showExit() {
        System.out.println("End of session Dawg. Goodbye Dawg.\n");
        showLine();
        sc.close();
    }
}
