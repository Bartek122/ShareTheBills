package pl.cholewa.sharethebills.billDetail;

import java.math.BigDecimal;

public record BillDetailResponse(
        BigDecimal price,
        String loginPayer
) {
}
