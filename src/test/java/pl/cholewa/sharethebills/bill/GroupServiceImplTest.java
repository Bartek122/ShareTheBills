package pl.cholewa.sharethebills.bill;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.cholewa.sharethebills.group.*;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {
    @Mock
    GroupRepository groupRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    GroupServiceImpl groupService;

    @Test
    public void whenCreateGroup_success() {
        User user = User.builder().login("kowal112").build();
        when(userRepository.findByLogin("kowal112")).thenReturn(Optional.of(user));
        GroupResponse groupResponse = groupService.create(new CreateGroupRequest("Grupka","kowal112"));

        assertThat(groupResponse)
                .hasFieldOrPropertyWithValue("name","Grupka");
    }

    @Test
    public void whenCreateGroup_withNonExistingOwner_fail(){
        when(userRepository.findByLogin("kowal1122")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> groupService.create(new CreateGroupRequest("Grupka","kowal1122")));
        assertThat(exception).hasMessageContaining("User not exist");
    }
}
