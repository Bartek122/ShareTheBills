package pl.cholewa.sharethebills.billDetail;

import java.math.BigDecimal;

public record BillDetailSumResponse(

        BigDecimal price,
        String loginBorrower,
        String loginPayer,
        String group
) {
}
