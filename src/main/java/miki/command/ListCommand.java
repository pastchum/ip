package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.TaskList;
import miki.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        ui.showList(tasks);
        return;
    }
}
