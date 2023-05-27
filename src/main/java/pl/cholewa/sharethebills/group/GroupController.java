package pl.cholewa.sharethebills.group;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<GroupResponse> getAll(){
        List<GroupResponse> groupResponses = groupService.getAllGroupWithMapper();
        return groupResponses;
    }
    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody @Valid CreateGroupRequest request){
        GroupResponse groupResponse = groupService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroup(@PathVariable Long id, @RequestBody @Valid UpdateGroupRequest request){
        GroupResponse response = groupService.update(id,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);
        return ResponseEntity.ok().build();
    }


}
