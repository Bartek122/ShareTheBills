package pl.cholewa.sharethebills.user;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


public record UserResponse(
        String login,
        String firstName,
        String lastName,
        @Nullable List<UserAttribute> userAttributes
        //@Nullable String AttrType,
        //@Nullable String AttrCont
) {
}
