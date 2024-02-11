package databasemigration.core.error;

import databasemigration.core.utils.Globals;

public interface TaskErrorHandler {
    void handleError(Globals.TaskStep step,Throwable throwable);
}
