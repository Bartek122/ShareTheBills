package pl.cholewa.sharethebills.bill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pl.cholewa.sharethebills.billDetail.BillDetailRepository;
import pl.cholewa.sharethebills.group.Group;
import pl.cholewa.sharethebills.group.GroupRepository;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BillServiceImplTest {
    @Mock
    BillDetailRepository billDetailRepository;
    @Mock
    BillRepository billRepository;
    @Mock
    GroupRepository groupRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks BillServiceImpl billService;


//    @Test
//    public void whenCreateBill_thenSuccess() {
//        User payer = User.builder().login("nowak1212").build();
//        when(userRepository.findByLogin("nowak1212")).thenReturn(payer);
//        Group group = Group.builder().name("Grupa").build();
//        when(groupRepository.findByName("Grupa")).thenReturn(group);
//        //List<User> userList = List.of(User.builder().groupList(group).build());
//
//        BillResponse billResponse = billService
//                .insert(new CreateBillRequest("Dojazd",new BigDecimal(240.22),"Grupa","nowak1212"));
//    }
}
