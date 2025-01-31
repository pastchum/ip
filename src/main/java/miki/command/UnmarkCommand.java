package miki.command;

import miki.exception.MikiException;
import miki.exception.CheckException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;

import miki.ui.Ui;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.size()) {// check task number is within the range of available tasks
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
