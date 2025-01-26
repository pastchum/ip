package miki.command;

import miki.task.*;
import miki.ui.*;
import miki.exception.*;
import miki.storage.*;

public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MikiException;

    public boolean checkExit() {
        return this.isExit;
    }
}
