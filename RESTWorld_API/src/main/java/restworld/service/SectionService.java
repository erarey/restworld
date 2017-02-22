package restworld.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.EmployeeDto;
import restworld.dto.GuestDto;
import restworld.dto.SectionDto;
import restworld.mapper.SectionMapper;
import restworld.persistence.entity.Narrative;
import restworld.persistence.entity.Section;
import restworld.persistence.repository.SectionRepository;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<SectionDto> index() {
		return sectionRepository
				.findAll()
				.stream()
				.map(sectionMapper::toSectionDto)
				.collect(Collectors.toList());
	}

	public List<SectionDto> sorted(Sort sort) {
		return sectionRepository
				.findAll(sort)
				.stream()
				.map(sectionMapper::toSectionDto)
				.collect(Collectors.toList());
	}

	public Page<SectionDto> paged(Pageable pageable) {
		return sectionRepository
				.findAll(pageable)
				.map(sectionMapper::toSectionDto);
	}

	public List<SectionDto> byExample(SectionDto example) {
		return sectionRepository
				.findAll(Example.of(sectionMapper.toSection(example)))
				.stream()
				.map(sectionMapper::toSectionDto)
				.collect(Collectors.toList());
	}


	public SectionDto get(Long id) {
		idChecker.exists(id);
		return sectionMapper.toSectionDto(sectionRepository.findOne(id));
	}

	public Long post(SectionDto sectionDto) {
		return sectionRepository.save(sectionMapper.toSection(sectionDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return sectionRepository.exists(id);
		return false;
	}

	public void put(Long id, SectionDto sectionDto) {
		idChecker.exists(id);
		Section section = sectionMapper.toSection(sectionDto);
		section.setId(id);
		sectionRepository.save(section);
	}
	
	public void patch(Long id, SectionDto sectionDto) {
		idChecker.exists(id);
		sectionRepository.save(serviceUtilities.copyNonNullProperties(sectionMapper.toSection(sectionDto), sectionRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		sectionRepository.delete(id);
	}

}
