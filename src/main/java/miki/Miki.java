package miki;

import miki.task.*;
import miki.ui.*;
import miki.exception.*;
import miki.storage.Storage;

import java.util.*;

public class Miki {
    private static TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Miki() {
        ui = new Ui();
        try {
            storage = new Storage("./data/tasks.txt");
            tasks = new TaskList((storage.load()));
        } catch (MikiException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
            storage = new Storage("./data/tasks.txt");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showIntro();

        while (true) {
            String input = sc.nextLine();
            ui.showLine();
            if (input.toLowerCase().contains("bye")) {
                ui.showExit();
                break;
            }
            try {
                handleInput(input);
            } catch (MikiException e) {
                System.out.println(e.getMessage());
            }

            ui.showLine();
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Miki().run();
    }

    public void handleInput(String line) throws MikiException {
        if (line.length() == 0) {
            throw new NoCommandException("No command received Dawg. Try that again.");
        }
        String[] inputs = line.split(" ");
        if (inputs[0].toLowerCase().equals("list")) {
            if (inputs.length != 1) {
                System.out.println("Please pass in the correct number of arguments.");
            }
            if (tasks.size() == 0) { // account for no tasks
                System.out.println("There are no tasks to display.");
                return;
            }
            System.out.println("List of tasks: "); // print out tasks 1 by 1
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.getTask(i).toString());
            }
            return;
        } else if (inputs[0].toLowerCase().contains("mark")) {
            if (inputs.length != 2) {// check that the unmark function is properly called
                throw new CheckException("Please pass in the correct number of arguments.");
            }
            int taskNumber = Integer.parseInt(inputs[1]);
            if (taskNumber > tasks.size()) {// check task number is within the range of available tasks
                throw new CheckException("The task you requested for does not exist.");
            }
            Task task = tasks.getTask(taskNumber - 1);
            if (inputs[0].toLowerCase().equals("mark")) {
                if (task.checkCompleted()) {
                    throw new CheckException(
                            "Task " + taskNumber + " has already been completed.\n");
                }
                task.toggleCompletion();
                ui.showTaskCompletion(task, taskNumber);
            } else {
                if (!task.checkCompleted()) {
                    throw new CheckException(
                            "Task " + taskNumber + " has yet to be completed.\n");
                }
                task.toggleCompletion();
                ui.showTaskCompletion(task, taskNumber);
            }
            storage.save(tasks.getTaskList());
            return;
        }
        if (inputs[0].toLowerCase().contains("delete")) {
            if (inputs.length != 2) {
                System.out.println(inputs.length);
                throw new DeleteFailedException("Please pass in the correct number of arguments.");
            }
            if (tasks.size() == 0) {// check task number is within the range of available tasks
                throw new DeleteFailedException("There are no tasks to delete.");
            }
            int taskNumber = Integer.parseInt(inputs[1]);
            if (taskNumber > tasks.size()) {// check task number is within the range of available tasks
                throw new DeleteFailedException("The task you requested for does not exist.");
            }
            Task task = tasks.getTask(taskNumber - 1);
            tasks.deleteTask(taskNumber - 1);
            System.out.println(
                    "Task " + taskNumber + " has been deleted Dawg.\n"
                            + task.toString());
            storage.save(tasks.getTaskList());
            return;

        }
        if (tasks.size() == 100) {
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
            tasks.addTask(task);
            System.out.println(
                    "Event task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            storage.save(tasks.getTaskList());
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
            tasks.addTask(task);
            System.out.println(
                    "Deadline task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            storage.save(tasks.getTaskList());
            return;
        } else if (inputs[0].toLowerCase().equals("todo")) {
            StringBuilder descBuilder = new StringBuilder();
            for (int i = 1; i < inputs.length; i++) {
                descBuilder.append(inputs[i]).append(" ");
            }
            Task task = new ToDo(descBuilder.toString());

            tasks.addTask(task);
            System.out.println(
                    "ToDo task has been added Dawg.\n"
                            + task.toString() + "\n"
                            + "You now have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + ".\n"
                            + "Get to work Dawg.");
            storage.save(tasks.getTaskList());
            return;
        } else {
            throw new InvalidTaskException("What are you saying Dawg.\n");
        }

    }
}
