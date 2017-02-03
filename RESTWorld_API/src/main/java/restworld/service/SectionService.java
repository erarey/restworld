package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.SectionDto;
import restworld.mapper.SectionMapper;
import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.persistence.repository.SectionRepository;

@Service
public class SectionService {

	SectionRepository sectionRepository;
	SectionMapper sectionMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper, ServiceUtilities serviceUtilities) {
		super();
		this.sectionRepository = sectionRepository;
		this.sectionMapper = sectionMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Narrative.class, this::has);
	}

	public SectionDto get(Long id) {
		idChecker.exists(id);
		return sectionMapper.sectionToSectionDto(sectionRepository.findOne(id));
	}

	public Long post(SectionDto sectionDto) {
		return sectionRepository.save(sectionMapper.sectionDtoToSection(sectionDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return sectionRepository.exists(id);
		return false;
	}

	public void put(Long id, SectionDto sectionDto) {
		idChecker.exists(id);
		Section section = sectionMapper.sectionDtoToSection(sectionDto);
		section.setId(id);
		sectionRepository.save(section);
	}
	
	public void patch(Long id, SectionDto sectionDto) {
		idChecker.exists(id);
		sectionRepository.save(serviceUtilities.copyNonNullProperties(sectionMapper.sectionDtoToSection(sectionDto), sectionRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		sectionRepository.delete(id);
	}

}
