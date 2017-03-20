package restworld.dto;

import javax.validation.constraints.NotNull;

import restworld.validation.group.RequiredFieldsNotNull;

//@Entity
public class GroupDto {

	
	//id removed for dto
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String name;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private Integer size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}


