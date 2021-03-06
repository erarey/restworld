package restworld.exception;

import restworld.persistence.entity.superclass.BaseEntity;

import java.io.Serializable;

public class ReferencedEntityNotFoundException extends RuntimeException {

	/**
	 * Required by RuntimeException, value generated by Eclipse
	 */
	private static final long serialVersionUID = -1439958071174318831L;

	public ReferencedEntityNotFoundException(Class<?> entityClass, Serializable id) {
		super("Cannot find [" + entityClass.getSimpleName() + "] with ID of [" + id + "]");
	}

}