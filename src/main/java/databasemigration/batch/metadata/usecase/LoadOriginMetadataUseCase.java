package databasemigration.batch.metadata.usecase;

import databasemigration.batch.metadata.entity.TableInfo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LoadOriginMetadataUseCase {
CompletableFuture<List<TableInfo>> loadMetadataBySchema(String schema);
}
