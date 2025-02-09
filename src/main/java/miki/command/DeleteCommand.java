package miki.command;

import miki.exception.MikiException;
import miki.exception.DeleteFailedException;

import miki.storage.Storage;

import miki.task.TaskList;
import miki.task.Task;

import miki.ui.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.getSize()) {// check task number is within the range of available tasks
            throw new DeleteFailedException("The task you requested to delete does not exist.");
        }
        Task task = tasks.getTask(taskNumber - 1);
        tasks.deleteTask(taskNumber - 1);
        storage.save(tasks.getTaskList());
        ui.showTaskDeleted(task, taskNumber);
        return;
    }
}
