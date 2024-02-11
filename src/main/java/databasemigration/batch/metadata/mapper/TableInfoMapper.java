package databasemigration.batch.metadata.mapper;

import databasemigration.batch.metadata.dto.ColumnMetadataDto;
import databasemigration.batch.metadata.entity.TableInfo;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface TableInfoMapper {
    public TableInfo map(ColumnMetadataDto columnMetadataDto);
    public List<TableInfo> mapList(List<ColumnMetadataDto> columnMetadataDtoList);
}
