package pl.cholewa.sharethebills.billDetail;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public record CreateBillDetailRequest(
        @DecimalMin(value="0.01") BigDecimal price
) {
}
