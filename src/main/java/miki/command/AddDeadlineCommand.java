package miki.command;

import java.time.LocalDateTime;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Deadline;
import miki.task.Task;
import miki.task.TaskList;
import miki.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    /**
     * Constructor for AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param deadline    Deadline of the deadline task.
     */
    public AddDeadlineCommand(String description, LocalDateTime deadline) {
        super(false);
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new Deadline(description, deadline);
        assert task != null : "Task should not be null";

        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        String output = ui.showTaskAdded(task, tasks.getSize());
        return output;
    }
}
