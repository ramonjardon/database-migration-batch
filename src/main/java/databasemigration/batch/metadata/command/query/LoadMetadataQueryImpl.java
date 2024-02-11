package databasemigration.batch.metadata.command.query;

import org.springframework.jdbc.core.JdbcTemplate;


public class LoadMetadataQueryImpl implements LoadMetadataQuery{
    private final JdbcTemplate jdbcTemplate;

    public LoadMetadataQueryImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
