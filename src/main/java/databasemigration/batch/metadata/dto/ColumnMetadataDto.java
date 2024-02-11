package databasemigration.batch.metadata.dto;

public record ColumnMetadataDto(String name, Integer size,String type,Boolean nullable,Boolean autoIncrement) {
}
