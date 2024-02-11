package databasemigration.batch.metadata.adapter.sql;

import databasemigration.batch.metadata.command.query.LoadMetadataQuery;
import databasemigration.batch.metadata.command.query.LoadMetadataQueryImpl;
import databasemigration.batch.metadata.dto.ColumnMetadataDto;
import databasemigration.batch.metadata.dto.SchemaDto;
import databasemigration.batch.metadata.port.output.LoadMetadataOutputPort;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
@Repository
@Scope("singleton")
public class MetadataJdbcAdapter implements LoadMetadataOutputPort {
    private final LoadMetadataQuery loadMetadataQuery;



    public MetadataJdbcAdapter(final JdbcTemplate jdbcTemplate) {
        this.loadMetadataQuery = new LoadMetadataQueryImpl(jdbcTemplate);

    }

    @Override
    public SchemaDto loadBySchemaName(String schema) {
        return null;
    }

    @Override
    public CompletableFuture<List<ColumnMetadataDto>> readColumnMetadata(String schema, String table) {
        return null;
    }
}
