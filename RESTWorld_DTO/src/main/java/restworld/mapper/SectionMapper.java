package restworld.mapper;

import org.mapstruct.Mapper;

import restworld.dto.SectionDto;
import restworld.persistence.entity.Section;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface SectionMapper {

	SectionDto sectionToSectionDto(Section section);
	
	Section sectionDtoToSection(SectionDto sectionDto);
}
