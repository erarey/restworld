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
import restworld.dto.NarrativeDto;
import restworld.mapper.NarrativeMapper;
import restworld.persistence.entity.Narrative;
import restworld.persistence.repository.NarrativeRepository;

import java.util.List;
import java.util.stream.Collectors;

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

	public List<NarrativeDto> index() {
		return narrativeRepository
				.findAll()
				.stream()
				.map(narrativeMapper::toNarrativeDto)
				.collect(Collectors.toList());
	}

	public List<NarrativeDto> sorted(Sort sort) {
		return narrativeRepository
				.findAll(sort)
				.stream()
				.map(narrativeMapper::toNarrativeDto)
				.collect(Collectors.toList());
	}

	public Page<NarrativeDto> paged(Pageable pageable) {
		return narrativeRepository
				.findAll(pageable)
				.map(narrativeMapper::toNarrativeDto);
	}

	public List<NarrativeDto> byExample(NarrativeDto example) {
		return narrativeRepository
				.findAll(Example.of(narrativeMapper.toNarrative(example)))
				.stream()
				.map(narrativeMapper::toNarrativeDto)
				.collect(Collectors.toList());
	}


	public NarrativeDto get(Long id) {
		idChecker.exists(id);
		return narrativeMapper.toNarrativeDto(narrativeRepository.findOne(id));
	}

	public Long post(NarrativeDto narrativeDto) {
		return narrativeRepository.save(narrativeMapper.toNarrative(narrativeDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return narrativeRepository.exists(id);
		return false;
	}

	public void put(Long id, NarrativeDto narrativeDto) {
		idChecker.exists(id);
		Narrative narrative = narrativeMapper.toNarrative(narrativeDto);
		narrative.setId(id);
		narrativeRepository.save(narrative);
	}
	
	public void patch(Long id, NarrativeDto narrativeDto) {
		idChecker.exists(id);
		narrativeRepository.save(serviceUtilities.copyNonNullProperties(narrativeMapper.toNarrative(narrativeDto), narrativeRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		narrativeRepository.delete(id);
	}

}
