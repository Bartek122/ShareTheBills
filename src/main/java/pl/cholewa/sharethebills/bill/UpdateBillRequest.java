package pl.cholewa.sharethebills.bill;

import pl.cholewa.sharethebills.user.User;

import java.math.BigDecimal;

public record UpdateBillRequest(

        BigDecimal price,
        String description
) {
}
