package pl.cholewa.sharethebills.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.cholewa.sharethebills.billDetail.BillDetail;
import pl.cholewa.sharethebills.billDetail.BillDetailSumResponse;

@Mapper
public interface BillDetailMapper {
    BillDetailMapper INSTANCE = Mappers.getMapper(BillDetailMapper.class);
    @Mapping(source= "borrower.login", target= "loginBorrower")
    @Mapping(source= "masterBill.payer.login", target= "loginPayer")
    @Mapping(source= "masterBill.group.name", target= "group")
    BillDetailSumResponse mapToBillDetailSum(BillDetail billDetail);

}
