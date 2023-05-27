package pl.cholewa.sharethebills.group;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source= "name", target= "name")
    List<GroupResponse> mapToGroupResponse(List<Group> group);


}
