package pl.cholewa.sharethebills.bill;


import pl.cholewa.sharethebills.user.User;

import java.util.List;

public interface BillService {

    BillResponse insert (CreateBillRequest billRequest);
    List<BillResponse> getAllByPayer (String login);
    BillResponse update (Long id,UpdateBillRequest billRequest);

    void billDelete(Long id);

    List<BillResponse> getAll();
}

