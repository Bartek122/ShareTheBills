package pl.cholewa.sharethebills.group;

import org.hibernate.sql.Update;

import java.util.List;

public interface GroupService {
    List<GroupResponse> getAllGroupWithMapper();
    GroupResponse create(CreateGroupRequest request);

    GroupResponse update(Long id,UpdateGroupRequest request);

    void delete(Long id);

}
