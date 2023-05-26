package pl.cholewa.sharethebills.billDetail;

import java.util.List;

public interface BillDetailService {
    List<BillDetailResponse> getSumByBorrower(BillDetailRequest billDetailRequest);
    void updateBillDetails(Long id, UpdateBillDetailRequest request);

}
