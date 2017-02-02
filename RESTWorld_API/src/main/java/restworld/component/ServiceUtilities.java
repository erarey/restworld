package restworld.component;

import java.lang.reflect.Field;
import java.util.function.Function;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import restworld.exception.ReferencedEntityNotFoundException;
import restworld.persistence.entity.superclass.BaseEntity;

@Component
public class ServiceUtilities {

	EntityManager entityManager;

	public ServiceUtilities(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public <T> T copyNonNullProperties(T source, T target) {
		if (target == null)
			return null;
		else if (source == null)
			return target;

		final BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
		final BeanWrapper targetWrapper = new BeanWrapperImpl(target);

		for (final Field property : target.getClass().getDeclaredFields()) {
			if (sourceWrapper.isReadableProperty(property.getName())
					&& targetWrapper.isWritableProperty(property.getName())) {

				Object providedObject = sourceWrapper.getPropertyValue(property.getName());

				if (providedObject != null) {
					if (sourceWrapper.getPropertyType(property.getName()).isAnnotationPresent(Embeddable.class)) {
						providedObject = copyNonNullProperties(sourceWrapper.getPropertyValue(property.getName()),
								targetWrapper.getPropertyValue(property.getName()));
					} else if (sourceWrapper.getPropertyType(property.getName()).isInstance(BaseEntity.class)) {
						Object referencedEntity = entityManager.find(sourceWrapper.getPropertyType(property.getName()),
								BaseEntity.class.cast(sourceWrapper.getPropertyValue(property.getName())).getId());
						providedObject = referencedEntity;
					}
					targetWrapper.setPropertyValue(property.getName(), providedObject);
				}
			}
		}
		return target;
	}

	public IdChecker buildIdChecker(Class<?> clazz, Function<Long, Boolean> has) {
		return new IdChecker(clazz, has);
	}

	public class IdChecker {

		Class<?> type;
		Function<Long, Boolean> has;

		protected IdChecker(Class<?> type, Function<Long, Boolean> has) {
			super();
			this.type = type;
			this.has = has;
		}

		public Boolean exists(Long id) {
			return exists(id, false);
		}

		public Boolean exists(Long id, Boolean suppressException) {
			if (id == null || !has.apply(id))
				if (suppressException)
					return false;
				else
					throw new ReferencedEntityNotFoundException(type, id);
			return true;
		}

	}
}
