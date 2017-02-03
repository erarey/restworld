package restworld.mapper;

import org.mapstruct.Mapper;

import restworld.dto.NarrativeDto;
import restworld.persistence.entity.Narrative;

@Mapper(componentModel = "spring", uses = { ReferenceMapper.class })
public interface NarrativeMapper {

	NarrativeDto narrativeToNarrativeDto(Narrative narrative);
	
	Narrative narrativeDtoToNarrative(NarrativeDto narrativeDto);
}
