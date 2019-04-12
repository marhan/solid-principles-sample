package de.marhan.sample.solid.adapter.rest.well;

import java.io.Serializable;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "All details about the apartment. ")
class WellApartmentResource implements Serializable {

    public enum Status {
        free, reserved, rented
    }

    @ApiModelProperty(notes = "The unique id of the apartment.")
    private UUID apartmentId;

    @ApiModelProperty(notes = "The city where the apartment is located.")
    private String city;

    @ApiModelProperty(notes = "The street where the apartment is located.")
    private String street;

    @ApiModelProperty(notes = "The booking status of the apartment.")
    private Status status;

}
