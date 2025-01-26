package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.*;
import miki.ui.Ui;

public class AddEventCommand extends Command {
    private String description;
    private String start;
    private String end;

    public AddEventCommand(String description, String start, String end) {
        super(false);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new Event(description, start, end);

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTaskList());
        return;
    }
}
