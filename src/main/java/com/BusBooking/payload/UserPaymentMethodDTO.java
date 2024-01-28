package com.BusBooking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserPaymentMethodDTO {
    private Long id;
    private Long userId;

    @NotEmpty(message = "Payment method cannot be empty")
    @Size(min = 2, max = 50, message = "Payment method must be between 2 and 50 characters")
    private String paymentMethod;

    @NotEmpty(message = "Card number cannot be empty")
    @Size(min = 2, max = 19, message = "Card number must be between 2 and 19 characters")
    private String cardNumber;

    @NotEmpty(message = "Expiration date cannot be empty")
    private Date expirationDate;

    @NotEmpty(message = "Card holder name cannot be empty")
    @Size(min = 2, max = 100, message = "Card holder name must be between 2 and 100 characters")
    private String cardHolderName;
}

// dev 3 no