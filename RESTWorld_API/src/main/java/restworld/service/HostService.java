package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.HostDto;
import restworld.mapper.HostMapper;
import restworld.persistence.entity.Host;
import restworld.persistence.repository.HostRepository;

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

