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
import restworld.mapper.GuestMapper;
import restworld.persistence.entity.Guest;
import restworld.persistence.entity.Host;
import restworld.persistence.repository.GuestRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService {

	GuestRepository guestRepository;
	GuestMapper guestMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public GuestService(GuestRepository guestRepository, GuestMapper guestMapper, ServiceUtilities serviceUtilities) {
		super();
		this.guestRepository = guestRepository;
		this.guestMapper = guestMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Host.class, this::has);
	}

	public List<GuestDto> index() {
		return guestRepository
				.findAll()
				.stream()
				.map(guestMapper::toGuestDto)
				.collect(Collectors.toList());
	}

	public List<GuestDto> sorted(Sort sort) {
		return guestRepository
				.findAll(sort)
				.stream()
				.map(guestMapper::toGuestDto)
				.collect(Collectors.toList());
	}

	public Page<GuestDto> paged(Pageable pageable) {
		return guestRepository
				.findAll(pageable)
				.map(guestMapper::toGuestDto);
	}

	public List<GuestDto> byExample(GuestDto example) {
		return guestRepository
				.findAll(Example.of(guestMapper.toGuest(example)))
				.stream()
				.map(guestMapper::toGuestDto)
				.collect(Collectors.toList());
	}


	public GuestDto get(Long id) {
		idChecker.exists(id);
		return guestMapper.toGuestDto(guestRepository.findOne(id));
	}

	public Long post(GuestDto guestDto) {
		return guestRepository.save(guestMapper.toGuest(guestDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return guestRepository.exists(id);
		return false;
	}

	public void put(Long id, GuestDto guestDto) {
		idChecker.exists(id);
		Guest guest = guestMapper.toGuest(guestDto);
		guest.setId(id);
		guestRepository.save(guest);
	}
	
	public void patch(Long id, GuestDto guestDto) {
		idChecker.exists(id);
		guestRepository.save(serviceUtilities.copyNonNullProperties(guestMapper.toGuest(guestDto), guestRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		guestRepository.delete(id);
	}
}
