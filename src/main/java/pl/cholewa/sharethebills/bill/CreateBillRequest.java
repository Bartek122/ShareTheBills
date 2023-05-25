package pl.cholewa.sharethebills.bill;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record CreateBillRequest(

        @NotBlank @Size(max = 1024) String description,
        @DecimalMin(value="0.01") BigDecimal price,
        @NotBlank String groupName,
        @NotBlank String loginPayer
) {
}
