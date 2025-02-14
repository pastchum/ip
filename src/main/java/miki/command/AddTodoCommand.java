package miki.command;

import miki.exception.MikiException;

import miki.storage.Storage;
import miki.task.Task;
import miki.task.TaskList;
import miki.task.ToDo;

import miki.ui.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 */
public class AddToDoCommand extends Command {
    private String description;

    /**
     * Constructor for AddToDoCommand.
     * 
     * @param description Description of the ToDo task.
     */
    public AddToDoCommand(String description) {
        super(false);
        this.description = description;
    }

    /**
     * Executes the command to add a ToDo task.
     * 
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new ToDo(description);

        tasks.addTask(task);
        storage.save(tasks.getTaskList());
        String output = ui.showTaskAdded(task, tasks.getSize());
        return output;
    }
}
