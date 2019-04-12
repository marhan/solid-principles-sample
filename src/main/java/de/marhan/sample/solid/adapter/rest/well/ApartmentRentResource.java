package de.marhan.sample.solid.adapter.rest.well;

import java.io.Serializable;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Reservation for an apartment.")
class ApartmentRentResource implements Serializable {

    @ApiModelProperty(notes = "The apartment ID.")
    private UUID apartmentId;

}
