package pl.cholewa.sharethebills.bill;

import pl.cholewa.sharethebills.user.User;

import java.math.BigDecimal;

public record UpdateBillRequest(
        Long id,
        BigDecimal price,
        String description,
        User borrower,
        Bill masterBill
) {
}
