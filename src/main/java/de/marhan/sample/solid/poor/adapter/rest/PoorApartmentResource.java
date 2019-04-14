package de.marhan.sample.solid.poor.adapter.rest;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "All details about the apartment. ")
class PoorApartmentResource implements Serializable {

	public enum Status {
		free, reserved, rented
	}

	@ApiModelProperty(notes = "The id of the apartment.")
	private Integer id;

	@ApiModelProperty(notes = "The city where the apartment is located.")
	private String city;

	@ApiModelProperty(notes = "The street where the apartment is located.")
	private String street;

	@ApiModelProperty(notes = "The booking status of the apartment.")
	private Status status;

}
