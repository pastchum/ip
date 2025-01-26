package miki.ui;

import java.util.Scanner;

import miki.task.Task;

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

    public void showTaskCompletion(Task task, int taskNumber) {
        boolean isCompleted = task.checkCompleted();
        System.out.println(
                "Task " + taskNumber + (isCompleted ? " completed.\n" : " is not completed.\n")
                        + task.toString());
    }

    public void showExit() {
        System.out.println("End of session Dawg. Goodbye Dawg.\n");
        showLine();
        sc.close();
    }
}
