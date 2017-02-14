package restworld.mapper;

import org.mapstruct.Mapper;

import restworld.datatype.Reference;
import restworld.dto.SectionDto;
import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface SectionMapper {

	SectionDto toSectionDto(Section section);
	
	Section toSection(SectionDto sectionDto);
}
