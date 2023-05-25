package pl.cholewa.sharethebills.bill;

import pl.cholewa.sharethebills.user.User;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record UpdateBillRequest(

        @DecimalMin(value="0.01")
        BigDecimal price,
        String description
) {
}
