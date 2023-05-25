package pl.cholewa.sharethebills.billDetail;

import java.math.BigDecimal;

public record CreateBillDetailRequest(
        BigDecimal price
) {
}
