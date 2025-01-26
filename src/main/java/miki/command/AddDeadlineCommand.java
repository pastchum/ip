package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.*;
import miki.ui.Ui;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadline;

    public AddDeadlineCommand(String description, String deadline) {
        super(false);
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new Deadline(description, deadline);

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTaskList());
        return;
    }
}