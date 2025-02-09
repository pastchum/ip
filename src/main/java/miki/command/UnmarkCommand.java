package miki.command;

import miki.exception.MikiException;
import miki.exception.CheckException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;

import miki.ui.Ui;

/**
 * Represents a command to unmark a task as completed.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for UnmarkCommand.
     * 
     * @param taskNumber The task number to unmark as completed.
     */
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to unmark a task as completed.
     * 
     * @param tasks   List of tasks.
     * @param ui      Ui object.
     * @param storage Storage object.
     * @throws MikiException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.getSize()) {// check task number is within the range of available tasks
            throw new CheckException("The task you requested to unmark does not exist.");
        }
        Task task = tasks.getTask(taskNumber - 1);
        if (!task.checkCompleted()) {
            throw new CheckException(
                    "Task " + taskNumber + " has yet to be completed.\n");
        }
        task.toggleCompletion();
        ui.showTaskCompletion(task, taskNumber);
    }
}
