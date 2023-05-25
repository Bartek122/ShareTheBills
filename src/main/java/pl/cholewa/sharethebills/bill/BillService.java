package pl.cholewa.sharethebills.bill;


import pl.cholewa.sharethebills.user.User;

import java.util.List;

public interface BillService {

    BillResponse insert (CreateBillRequest billRequest);
    List<BillResponse> getAllbyPayer (String login);
    BillResponse update (UpdateBillRequest billRequest);
}

