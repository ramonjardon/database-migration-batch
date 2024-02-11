package databasemigration.batch.metadata.task;

import databasemigration.batch.metadata.entity.TableInfo;
import databasemigration.batch.metadata.error.LoadMetadataInfoErrorHandler;
import databasemigration.batch.metadata.usecase.LoadOriginMetadataUseCase;
import databasemigration.core.utils.Globals;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.batch.repeat.RepeatStatus.*;

/**
 * LoadMetadataTask
 */
@Component("loadMetadataTask")
public class LoadMetadataTask implements Tasklet, StepExecutionListener {

    private final CopyOnWriteArrayList<TableInfo> context = new CopyOnWriteArrayList<>();

    private final LoadOriginMetadataUseCase loadOriginMetadataUseCase;

    private final LoadMetadataInfoErrorHandler errorHandler;

    /**
     *
     * @param loadOriginMetadataUseCase
     * @param errorHandler
     */
    public LoadMetadataTask(final LoadOriginMetadataUseCase loadOriginMetadataUseCase, final LoadMetadataInfoErrorHandler errorHandler) {
        this.loadOriginMetadataUseCase = loadOriginMetadataUseCase;
        this.errorHandler = errorHandler;
    }

    /**
     * @param contribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<String> schemaNames= List.of("1");
        schemaNames.forEach(schema -> loadOriginMetadataUseCase.loadMetadataBySchema(schema)
                .thenApply(context::addAll)
                .exceptionally(throwable -> {
                    errorHandler.handleError(Globals.TaskStep.LOAD_METADATA, throwable);
                    return null;
                }));


        return FINISHED;
    }

}
