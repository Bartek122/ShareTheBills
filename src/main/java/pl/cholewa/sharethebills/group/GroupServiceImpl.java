package pl.cholewa.sharethebills.group;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.cholewa.sharethebills.user.User;
import pl.cholewa.sharethebills.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService{
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    @Override
    public List<GroupResponse> getAllGroupWithMapper() {
        return GroupMapper.INSTANCE.mapToGroupResponse(groupRepository.findAllBy());
    }

    @Override
    @Transactional
    public GroupResponse create(CreateGroupRequest request) {
        User user = userRepository.findByLogin(request.login());
        Group group = Group
                .builder()
                .name(request.groupName())
                .owner(user)
                .build();
        groupRepository.save(group);
        return toResponse(group);
    }

    @Override
    @Transactional
    public GroupResponse update(Long id,UpdateGroupRequest request) {
        return groupRepository.findById(id)
                        .map(group -> { group.setName(request.name());
            return group;
        }).map(groupRepository::save)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Group does not exist id " + id ));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    private GroupResponse toResponse(Group group){
        return new GroupResponse(
                group.getName()
        );
    }



}

