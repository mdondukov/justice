package kg.biom.justice.model.mapper;

import kg.biom.justice.model.dto.PageDto;
import kg.biom.justice.model.entity.PageViewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PageMapper {
    PageDto toDto(PageViewEntity entity);
}
