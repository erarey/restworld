package restworld.service;

import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.GuestDto;
import restworld.mapper.GuestMapper;
import restworld.persistence.entity.Guest;
import restworld.persistence.entity.Host;
import restworld.persistence.repository.GuestRepository;

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

	public GuestDto get(Long id) {
		idChecker.exists(id);
		return guestMapper.guestToGuestDto(guestRepository.findOne(id));
	}

	public Long post(GuestDto guestDto) {
		return guestRepository.save(guestMapper.guestDtoToGuest(guestDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return guestRepository.exists(id);
		return false;
	}

	public void put(Long id, GuestDto guestDto) {
		idChecker.exists(id);
		Guest guest = guestMapper.guestDtoToGuest(guestDto);
		guest.setId(id);
		guestRepository.save(guest);
	}
	
	public void patch(Long id, GuestDto guestDto) {
		idChecker.exists(id);
		guestRepository.save(serviceUtilities.copyNonNullProperties(guestMapper.guestDtoToGuest(guestDto), guestRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		guestRepository.delete(id);
	}
}
