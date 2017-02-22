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
import restworld.dto.HostDto;
import restworld.mapper.HostMapper;
import restworld.persistence.entity.Host;
import restworld.persistence.repository.HostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostService {

	HostRepository hostRepository;
	HostMapper hostMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public HostService(HostRepository hostRepository, HostMapper hostMapper, ServiceUtilities serviceUtilities) {
		super();
		this.hostRepository = hostRepository;
		this.hostMapper = hostMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Host.class, this::has);
	}

	public List<HostDto> index() {
		return hostRepository
				.findAll()
				.stream()
				.map(hostMapper::toHostDto)
				.collect(Collectors.toList());
	}

	public List<HostDto> sorted(Sort sort) {
		return hostRepository
				.findAll(sort)
				.stream()
				.map(hostMapper::toHostDto)
				.collect(Collectors.toList());
	}

	public Page<HostDto> paged(Pageable pageable) {
		return hostRepository
				.findAll(pageable)
				.map(hostMapper::toHostDto);
	}

	public List<HostDto> byExample(HostDto example) {
		return hostRepository
				.findAll(Example.of(hostMapper.toHost(example)))
				.stream()
				.map(hostMapper::toHostDto)
				.collect(Collectors.toList());
	}


	public HostDto get(Long id) {
		idChecker.exists(id);
		return hostMapper.toHostDto(hostRepository.findOne(id));
	}

	public Long post(HostDto hostDto) {
		return hostRepository.save(hostMapper.toHost(hostDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return hostRepository.exists(id);
		return false;
	}

	public void put(Long id, HostDto hostDto) {
		idChecker.exists(id);
		Host host = hostMapper.toHost(hostDto);
		host.setId(id);
		hostRepository.save(host);
	}
	
	public void patch(Long id, HostDto hostDto) {
		idChecker.exists(id);
		hostRepository.save(serviceUtilities.copyNonNullProperties(hostMapper.toHost(hostDto), hostRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		hostRepository.delete(id);
	}
}

