package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.NarrativeDto;
import restworld.mapper.NarrativeMapper;
import restworld.persistence.entity.Narrative;
import restworld.persistence.repository.NarrativeRepository;

@Service
public class NarrativeService {

	NarrativeRepository narrativeRepository;
	NarrativeMapper narrativeMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public NarrativeService(NarrativeRepository narrativeRepository, NarrativeMapper narrativeMapper, ServiceUtilities serviceUtilities) {
		super();
		this.narrativeRepository = narrativeRepository;
		this.narrativeMapper = narrativeMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Narrative.class, this::has);
	}

	public NarrativeDto get(Long id) {
		idChecker.exists(id);
		return narrativeMapper.narrativeToNarrativeDto(narrativeRepository.findOne(id));
	}

	public Long post(NarrativeDto narrativeDto) {
		return narrativeRepository.save(narrativeMapper.narrativeDtoToNarrative(narrativeDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return narrativeRepository.exists(id);
		return false;
	}

	public void put(Long id, NarrativeDto narrativeDto) {
		idChecker.exists(id);
		Narrative narrative = narrativeMapper.narrativeDtoToNarrative(narrativeDto);
		narrative.setId(id);
		narrativeRepository.save(narrative);
	}
	
	public void patch(Long id, NarrativeDto narrativeDto) {
		idChecker.exists(id);
		narrativeRepository.save(serviceUtilities.copyNonNullProperties(narrativeMapper.narrativeDtoToNarrative(narrativeDto), narrativeRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		narrativeRepository.delete(id);
	}

}
