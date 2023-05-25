package pl.cholewa.sharethebills.user;

import javax.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank String login,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String attrType,
        String attrContent

) {
}
