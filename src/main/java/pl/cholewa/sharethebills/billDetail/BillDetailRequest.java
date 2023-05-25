package pl.cholewa.sharethebills.billDetail;

public record BillDetailRequest(
        String loginBorrower,
        String groupName
) {
}
