package databasemigration.batch.metadata.dto;

import java.util.List;

public record SchemaDto(List<String> tables) {
}
