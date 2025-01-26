package miki.command;

import miki.exception.*;
import miki.task.*;
import miki.ui.*;
import miki.storage.*;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        if (taskNumber > tasks.size()) {// check task number is within the range of available tasks
            throw new DeleteFailedException("The task you requested to delete does not exist.");
        }
        Task task = tasks.getTask(taskNumber - 1);
        tasks.deleteTask(taskNumber - 1);
        storage.save(tasks.getTaskList());
        ui.showTaskDeleted(task, taskNumber);
        return;
    }
}
