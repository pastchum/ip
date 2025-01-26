package miki.command;

import miki.exception.MikiException;
import miki.storage.Storage;
import miki.task.Task;
import miki.task.TaskList;
import miki.task.ToDo;
import miki.ui.Ui;

public class AddTodoCommand extends Command {
    private String description;

    public AddTodoCommand(String description) {
        super(false);
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException {
        Task task = new ToDo(description);

        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTaskList());
        return;
    }
}
