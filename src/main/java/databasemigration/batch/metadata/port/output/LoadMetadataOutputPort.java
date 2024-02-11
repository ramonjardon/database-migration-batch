package databasemigration.batch.metadata.port.output;

import databasemigration.batch.metadata.dto.ColumnMetadataDto;
import databasemigration.batch.metadata.dto.SchemaDto;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LoadMetadataOutputPort {
    SchemaDto loadBySchemaName(String schema);
    CompletableFuture<List<ColumnMetadataDto>> readColumnMetadata(String schema, String table);
}
