package miki;

import miki.task.*;
import miki.ui.*;
import miki.command.Command;
import miki.exception.*;
import miki.parse.Parser;
import miki.storage.Storage;

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
        ui.showIntro();

        while (true) {
            ui.showLine();
            String userCommand = ui.readCommand();
            if (userCommand.toLowerCase().contains("bye")) {
                ui.showExit();
                break;
            }
            try {
                Command command = Parser.handleUserCommand(userCommand);
                command.execute(tasks, ui, storage);
                if (command.checkExit())
                    break;
            } catch (MikiException e) {
                System.out.println(e.getMessage());
            }

            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Miki().run();
    }
}
