package restworld.mapper;

import org.mapstruct.Mapper;

import restworld.datatype.Reference;
import restworld.dto.NarrativeDto;
import restworld.persistence.entity.Narrative;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface NarrativeMapper {

	NarrativeDto toNarrativeDto(Narrative narrative);
	
	Narrative toNarrative(NarrativeDto narrativeDto);
}
