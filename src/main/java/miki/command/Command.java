package miki.command;

import miki.exception.MikiException;

import miki.storage.Storage;

import miki.task.TaskList;

import miki.ui.Ui;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException;

    public boolean checkExit() {
        return this.isExit;
    }
}
