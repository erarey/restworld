package restworld.persistence.entity.superclass;

import java.io.Serializable;

public interface BaseEntity<Id extends Serializable> {

	public Id getId();

	public void setId(Id id);

}
