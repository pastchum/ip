package miki.command;

import java.time.LocalDateTime;

import miki.exception.MikiException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;
import miki.task.Event;

import miki.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for AddEventCommand.
     * 
     * @param description Description of the event task.
     * @param start       Start time of the event task.
     * @param end         End time of the event task.
     */
    public AddEventCommand(String description, LocalDateTime start, LocalDateTime end) {
        super(false);
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the command to add an event task.
     * 
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new Event(description, start, end);

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.getSize());
        storage.save(tasks.getTaskList());
        return;
    }
}
