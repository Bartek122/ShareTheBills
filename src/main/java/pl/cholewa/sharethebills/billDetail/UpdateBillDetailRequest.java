package pl.cholewa.sharethebills.billDetail;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record UpdateBillDetailRequest(
        @DecimalMin(value = "0.00") BigDecimal amount
) {
}
