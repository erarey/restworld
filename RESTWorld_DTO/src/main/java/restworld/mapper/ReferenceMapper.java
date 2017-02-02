package restworld.mapper;

import javax.persistence.EntityManager;

import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import restworld.datatype.Reference;
import restworld.exception.ReferencedEntityNotFoundException;
import restworld.persistence.entity.superclass.BaseEntity;

@Component
public class ReferenceMapper {

    private final EntityManager entityManager;

    public ReferenceMapper(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public <T extends BaseEntity> T resolve(Reference reference, @TargetType Class<T> entityClass) {
        if(reference != null) {
        	T result = entityManager.find( entityClass, reference.getId() );
        	if(result == null)
        		throw new ReferencedEntityNotFoundException(entityClass, reference.getId());
        	return result;
        }
		return null;
    }

    public Reference toReference(BaseEntity entity) {
        return entity != null ? new Reference( entity.getId() ) : null;
    }

}