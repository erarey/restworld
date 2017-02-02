package restworld.service;

import org.springframework.stereotype.Service;

import restworld.dto.SectionDto;
import restworld.mapper.SectionMapper;
import restworld.persistence.repository.SectionRepository;

@Service
public class SectionService {

	private SectionRepository sectionRepository;
	private SectionMapper sectionMapper;
	
	public SectionService(SectionRepository sectionRepository, SectionMapper sectionMapper) {
		super();
		this.sectionRepository = sectionRepository;
		this.sectionMapper = sectionMapper;
	}

	public SectionDto get(Long id) {
		if(id == null)
			return null;
		return sectionMapper.sectionToSectionDto(sectionRepository.getOne(id));
	}

	public Long post(SectionDto sectionDto) {
		return sectionRepository.save(sectionMapper.sectionDtoToSection(sectionDto)).getId();
	}

}
