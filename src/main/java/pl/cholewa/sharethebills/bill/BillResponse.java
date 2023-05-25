package pl.cholewa.sharethebills.bill;

import java.math.BigDecimal;

public record BillResponse(
        String description,
        BigDecimal price
) {
}
