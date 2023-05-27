package pl.cholewa.sharethebills.group;

import javax.validation.constraints.NotBlank;

public record CreateGroupRequest(
    @NotBlank String groupName,
    @NotBlank String login
) {
}
