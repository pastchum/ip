package miki.command;

import miki.exception.MikiException;

import miki.storage.Storage;

import miki.task.TaskList;

import miki.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        return;
    }
}
