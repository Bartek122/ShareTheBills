package pl.cholewa.sharethebills.bill;

import java.math.BigDecimal;

public record CreateBillRequest(
        String description,
        BigDecimal price,
        String groupName,
        String loginPayer
) {
}
