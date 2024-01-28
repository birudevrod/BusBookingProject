package com.BusBooking.payload;

import com.BusBooking.entities.BusOperator;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class BusDTO {

    private  Long id;
    private Long operatorId;

    @NotEmpty(message = "Bus type cannot be empty")
    @Size(min = 2, max = 50, message = "Bus type must be between 2 and 50 characters")
    private String busType;

    @NotEmpty(message = "Total seats cannot be empty")
    @Min(value = 1, message = "Total seats must be at least 1")
    private  Integer totalSeats;


    private  String amenities;
}
//dev 5 no