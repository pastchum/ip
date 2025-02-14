package miki;

import miki.command.Command;
import miki.exception.MikiException;
import miki.parse.Parser;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents the main class of the Miki chatbot.
 */
public class Miki {
    private static TaskList tasks;
    private Storage storage;
    private Ui ui;

    private boolean isExit = false;

    /**
     * Constructor for Miki.
     */
    public Miki() {
        ui = new Ui();
        try {
            storage = new Storage("./data/tasks.txt");
            tasks = new TaskList((storage.load()));
        } catch (MikiException e) {
            System.out.println("Error loading tasks from file. Starting with an empty task list. " + e.getMessage());
            tasks = new TaskList();
            storage = new Storage("./data/tasks.txt");
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.handleUserCommand(input);
            String response = command.execute(tasks, ui, storage);

            if (command.checkExit()) {
                isExit = true;
            }

            return response;
        } catch (MikiException e) {
            return e.getMessage();
        }
    }

    public boolean checkExit() {
        return isExit;
    }

    /**
     * Runs the Miki chatbot. (This method is only used for the CLI version of Miki)
     */
    public void run() {
        ui.showIntro();

        while (true) {
            ui.showLine();
            String userCommand = ui.readCommand();
            try {
                Command command = Parser.handleUserCommand(userCommand);
                command.execute(tasks, ui, storage);
                if (command.checkExit()) {
                    ui.showExit();
                    break;
                }
            } catch (MikiException e) {
                ui.showErrorMessage(e.getMessage());
            }

            ui.showLine();
        }
    }
}
