package com.BusBooking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class BusOperatorDTO {
    private Long id;



    @NotEmpty(message = "Operator name cannot be empty")
    @Size(min = 2, max = 100, message = "Operator name must be between 2 and 100 characters")
    private String operatorName;


    @NotEmpty(message = "Invalid email format")
    @Email(message = "Invalid email format")
    private String contactEmail;


    @NotEmpty(message = "Contact phone cannot be empty")
    @Size(min = 10, max = 15, message = "Contact phone must be between 10 and 15 characters")
    private String contactPhone;

    private String logoUrl;


}
//dev 4 no