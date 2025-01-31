package miki.command;

import java.time.LocalDate;

import miki.exception.MikiException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;
import miki.task.Deadline;

import miki.ui.Ui;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate deadline;

    public AddDeadlineCommand(String description, LocalDate deadline) {
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