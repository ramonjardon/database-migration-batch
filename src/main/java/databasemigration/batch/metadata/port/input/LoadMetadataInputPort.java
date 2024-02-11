package databasemigration.batch.metadata.port.input;

import databasemigration.batch.metadata.dto.SchemaDto;
import databasemigration.batch.metadata.entity.TableInfo;
import databasemigration.batch.metadata.mapper.TableInfoMapper;
import databasemigration.batch.metadata.port.output.LoadMetadataOutputPort;
import databasemigration.batch.metadata.usecase.LoadOriginMetadataUseCase;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

@Scope("prototype")
@Service("loadMetadataInputPort")
public class LoadMetadataInputPort implements LoadOriginMetadataUseCase {
    private final LoadMetadataOutputPort loadMetadataOutputPort;

    private final TableInfoMapper tableInfoMapper;

    public LoadMetadataInputPort(final LoadMetadataOutputPort loadMetadataOutputPort, final TableInfoMapper tableInfoMapper) {
        this.loadMetadataOutputPort = loadMetadataOutputPort;
        this.tableInfoMapper = tableInfoMapper;
    }



    @Override
    @Async
    public CompletableFuture<List<TableInfo>> loadMetadataBySchema(String schema) {
       final SchemaDto schemaDto = loadMetadataOutputPort.loadBySchemaName(schema);
        CopyOnWriteArrayList<TableInfo> tableInfoList = new CopyOnWriteArrayList<>();
        callReadColumnMetadataRecursive(schema, schemaDto.tables(), tableInfoList);
        return CompletableFuture.completedFuture(tableInfoList);
    }

    private void callReadColumnMetadataRecursive(String schema, List<String> tables, CopyOnWriteArrayList<TableInfo> tableInfoList) {
        if (tables.isEmpty()) {
            return;
        }
        if (1 == tables.size()) {
            String table = tables.getFirst();
            loadMetadataOutputPort.readColumnMetadata(schema, table)
                    .thenApply(e -> tableInfoList.addAll(tableInfoMapper.mapList(e)));
          return;
        }

        callReadColumnMetadataRecursive(schema, tables.subList(1, tables.size()), tableInfoList);
    }
}
