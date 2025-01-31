package miki.command;

import java.time.LocalDate;

import miki.exception.MikiException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;
import miki.task.Event;

import miki.ui.Ui;

public class AddEventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    public AddEventCommand(String description, LocalDate start, LocalDate end) {
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
