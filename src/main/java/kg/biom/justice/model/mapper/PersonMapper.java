package kg.biom.justice.model.mapper;

import kg.biom.justice.model.dto.PersonDto;
import kg.biom.justice.model.entity.PersonViewEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "photo", ignore = true)
    PersonDto toDto(PersonViewEntity entity);

    @AfterMapping
    default void mapFullName(@MappingTarget PersonDto dto, PersonViewEntity entity) {
        StringBuilder name = new StringBuilder();
        name.append(entity.getFirstName());
        if (entity.getMiddleName() != null) {
            name.append(" ").append(entity.getMiddleName());
        }
        name.append(" ").append(entity.getLastName());
        dto.setName(name.toString().trim());
    }
}